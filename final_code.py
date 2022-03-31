import spidev               #library needed to read out the ADC
import mysql.connector      #library needed to connect to database
import time                 #library needed to wait

spi = spidev.SpiDev()
spi.open(0, 0)
spi.max_speed_hz = 1000000  
sensor1 = False             #both motion sensors are initially set to False
sensor2 = False

def readChannel(channel):   #reading out the value on a given channel of the ADC
    val = spi.xfer2([1, (8 + channel) << 4, 0])
    data = ((val[1] & 3) << 8) + val[2]

    return data

mydb = mysql.connector.connect(         #setup connection to sql database
    host ="mysql.studev.groept.be",
    user ="a21ib2c02",
    password ="secret",
    database = "a21ib2c02")


if __name__ == "__main__":
    while(True):
        v1 = (readChannel(0)) / 1023.0 * 3.3    #converting value of the ADC to a voltage
        v2 = (readChannel(1)) / 1023.0 * 5
        v3 = (readChannel(3)) / 1023.0 * 5


        if(v2<0.5):                             #first sensor is triggered
            sensor1= True
            if (sensor2==True):                 #second sensor is also triggered (person leaving)
                mycursor = mydb.cursor()
                sql = "UPDATE people SET number = number - 1 WHERE id= 1"       #update database with -1 people
                mycursor.execute(sql)
                mydb()
                sensor1 = False                 #set both sensors back to False
                sensor2 = False


        if(v3<0.5):                             #second sensor is triggered
            sensor2 = True
            if (sensor2==True):                 #first sensor is also triggered (person entering)
                mycursor = mydb.cursor()
                sql = "UPDATE people SET number = number + 1 WHERE id= 1"       #update database with +1 people
                mycursor.execute(sql)
                mydb.commit()
                sensor1 =False                  #set both sensors back to False
                sensor2 = False



        distance = 151.54*v1**4-526.84*v1**3+656.19*v1**2-367.48*v1+107.96        #convert voltage of distance sensor to distance(cm)
        amount = distance/8                                                        #amount of bottles that equals to the distance
        mycursor = mydb.cursor()
        sql = "UPDATE winestock SET number ={number} WHERE idstock = {idstock}".format(number = amount, idstock =1)    #update database with current amount of bottles
        mycursor.execute(sql)
        mydb.commit()
        time.sleep(1)     #wait 1 second before re-executing main