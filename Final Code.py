import spidev
import mysql.connector
import time

spi = spidev.SpiDev()
spi.open(0, 0)
spi.max_speed_hz = 1000000
sensor1 = True
sensor2 = False

def readChannel(channel):
    val = spi.xfer2([1, (8 + channel) << 4, 0])
    data = ((val[1] & 3) << 8) + val[2]

    return data

mydb = mysql.connector.connect(
    host ="mysql.studev.groept.be",
    user ="a21ib2c02",
    password ="secret",
    database = "a21ib2c02")

print(mydb)



if __name__ == "__main__":
    while(True):
        v1 = (readChannel(0)) / 1023.0 * 5
        v2 = (readChannel(1)) / 1023.0 * 5
        v3 = (readChannel(2)) / 1023.0 * 5


        if(v1<0.5):
            begintime = time.time()
            print("object in 1",begintime)
            sensor1= True
            if (sensor2==True):
                mycursor = mydb.cursor()
                sql = "UPDATE people SET number = number - 1 WHERE id= 1"
                mycursor.execute(sql)
                mydb()
                #print('person leaving')
                sensor1 = False
                sensor2 = False
        if(v2<0.5):
            endtime = time.time()
            #print("object in 2,",endtime)
            sensor2 = True
            if (sensor2==True):
                mycursor = mydb.cursor()
                sql = "UPDATE people SET number = number + 1 WHERE id= 1"
                #print('person coming in')
                mycursor.execute(sql)
                mydb.commit()
                sensor1 =False
                sensor2 = False

        distance = 16.25*v3**4-129.89*v3**3+282.268*v3**2-512.611*v3+301.439
        amount = distance/10
        mycursor3 = mydb.cursor
        sql3 = "UPDATE winestock SET number ={number} WHERE idstock = {idstock}".format(number = amount, idstock =1)
        mycursor3.excute(sql)
        mydb.commit()
        time.sleep(0.5)