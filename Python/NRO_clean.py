"""
Title:Data clean up of NRO and cables
Author:Lekun ZHUANG
Github:https://github.com/LekunZHUANG
"""
import csv

#提取csv文件中的某一特定列  Columnname:csv中的列名  Filename:csv文件名
def getColumn(Columnname, Filename):
    try:
        file = open(Filename, 'r')
        dReader = csv.DictReader(file)
        column = [row[Columnname] for row in dReader]
        #print(column)
        return column
    except Exception as e:
        print(e)

#寻找A列表中在B列表出现过的数据
#例如寻找有线连接的基站  AllNRO:所有基站  CB_ND1:端点
def FindPresence(A=[], B=[]):
    Presence = []
    try:
        for i in range(len(A)):
            if A[i] in B:
                Presence.append(A[i])
        #print(Presence)
        return Presence
    except Exception as e:
        print(e)

#寻找A列表中B列表未出现过的数据
#例如寻找没有线连接的基站 AllNRO:所有基站  CB_ND1:端点
def FindAbsence(A=[], B=[]):
    Absence = []
    try:
        for i in range(len(A)):
            if A[i] not in B:
                Absence.append(A[i])
        #print(Absence)
        return Absence
    except Exception as e:
        print(e)

#寻找未出现过的数据的下标
#这个函数到最后其实没用上:(
def FindAbsenceIndex(A = [], B = []):
    Index = []
    try:
        for i in range(len(A)):
            if A[i] not in B:
                Index.append(i)
        return Index
    except Exception as e:
        print(e)

#把没有线缆连接的基站信息写入csv表
def writeUNRO(UnLinkedNRO):
    with open("NRO.csv", 'r') as r:
        lines = r.readlines()
    with open("UnLinkedNRO.csv", 'w') as w:
        w.write(lines[0])
        for i in range(0, len(lines)):
            for j in range(0, len(UnLinkedNRO)):
                if UnLinkedNRO[j] in lines[i]:
                    w.write(lines[i])

#把有线缆连接的基站信息保留
def keepLNRO(LinkedNRO):
    with open("NRO.csv", 'r') as r:
        lines = r.readlines()
    # 若把‘LinkedNRO.csv’修改成‘NRO.csv’则可以在现有表中删除单独的NRO
    with open("LinkedNRO.csv", 'w') as w:
        w.write(lines[0])
        for i in range(0, len(lines)):
            for j in range(0, len(LinkedNRO)):
                if LinkedNRO[j] in lines[i]:
                    w.write(lines[i])

#寻找基站所连接的线缆数量
def FindCBNum(A = [], B = [], bcontent = str):
    number = 0
    for i in range(len(B)):
        if(B[i] == bcontent):
            number = number +1
    return number

#寻找A列表中和B列表中某数据的对应下标的数据
#例如寻找CB_ND1为86CAY 对应的CB_ID
def FindCB(A = [], B = [] , bcontent = str):
    list = []
    for i in range(len(B)):
        if(B[i] == bcontent):
            list.append(A[i])
    #print(list)
    return list


#知道线缆名字 寻找相应线缆规格，线缆长度，线缆另一端
#A:线缆名字  D:线缆规格 E:线缆长度  C:连接的东西
def FindPoint(A = [], D = [], E =[], C = [], a=str, list =[]):
    for i in range(len(A)):
        if(A[i] == a):
            list.append(A[i])
            list.append(D[i])
            list.append(E[i])
            list.append(C[i])
            break
    #print(list)
    return C[i]

#写入轮转机
#nro:输入的基站名字
#i用来确定前面需要输入的空白格子的数量  例如i=1时  说明要在前面补4个空格
def Turbo(i, j, nro=str):
    A = FindCB(CB_ID, CB_ND1, nro)
    for a in A:
        if a == A[0]:
            i = 0
            j = j + 1
        if a != A[0]:
            i = j
        if(i != 0):
            for k in range(0, 4*i):
                list.append('')   #补空格
        c = FindPoint(CB_ID, CB_CAPAFO, CB_LONG, CB_ND2, a, list)
        if(FindCBNum(CB_ID, CB_ND1, c)> 0):
            Turbo(i, j, c)
            #list.clear()
        if(FindCBNum(CB_ID, CB_ND1, c)==0):
            #print(list)
            writer.writerow(list)
            #print(i)
            list.clear()    #写完后清空list列表
            list.append('') #每一行前面再加一个空格


#将字符串列表B 转化成浮点型列表  再四舍五入成整型列表  最后再转换成字符串型列表输出
def FourFive(B):
    results = list(map(float, B)) #将字符串型转换为浮点型
    #print(results)
    for i in range(0, len(results)):
        results[i] = results[i] + float(0.5)   #将每一个浮点数据加0.5 以便四舍五入
    #print(results)
    res = list(map(int, results))              #将浮点型转换为整型 暴力四舍五入
    #print(res)
    B = list(map(str, res))                   #将整型再转换回字符串型
    return B
    #print(B)



# 把需要用到的列提取出来
#  AllNRO:所有的基站  LinkedNRO:有连接线缆的基站  UnLinkedNRO:没有连接线缆的基站
AllNRO = getColumn('pt_id', 'NRO.csv')

CB_ID = getColumn('CB_ID', 'NRO-PM(7,9,10).csv')
CB_ND1 = getColumn('CB_ND1', 'NRO-PM(7,9,10).csv')
CB_ND2 = getColumn('CB_ND2', 'NRO-PM(7,9,10).csv')
CB_CAPAFO = getColumn('CB_CAPAFO', 'NRO-PM(7,9,10).csv')
CB_LONG = getColumn('CB_LONG', 'NRO-PM(7,9,10).csv')
CB_LONG = FourFive(CB_LONG)  #四舍五入

LinkedNRO = FindPresence(AllNRO, CB_ND1)
UnLinkedNRO = FindAbsence(AllNRO, CB_ND1)

#把有线连接的基站写入csv表
csvFile =open('NROChemin.csv', "w", newline ='\n')
writer = csv.writer(csvFile)
heading = ['NRO', 'CBname', 'CBtype', 'CBlength', 'LinkedThing', 'CBname', 'CBtype', 'CBlength', 'LinkedThing',
           'CBname', 'CBtype', 'CBlength', 'LinkedThing', 'CBname', 'CBtype', 'CBlength', 'LinkedThing', 'CBname',
           'CBtype', 'CBlength', 'LinkedThing', 'CBname', 'CBtype', 'CBlength', 'LinkedThing',]
writer.writerow(heading)
for m in range(len(LinkedNRO)):
        list = []
        list.append(LinkedNRO[m])
        Turbo(0, -1, LinkedNRO[m])

#把没有线缆连接的基站重新写入NRO表中
writeUNRO(UnLinkedNRO)
keepLNRO(LinkedNRO)
