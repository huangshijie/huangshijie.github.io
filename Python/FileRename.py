#-- coding:UTF-8 --
import os

def fileRename(folder):
    fileList = os.listdir(folder) 
    for everyFile in fileList:
        filePath = os.path.join(folder, everyFile)
        if(os.path.isdir(filePath)):
            print(filePath.decode('utf-8').encode('utf-8'), " is a folder")
            fileRename(filePath)
        else:
            print(filePath.decode('utf-8').encode('utf-8'), " is a file")

if __name__=="__main__":
    root = '/Volumes/Untitled/电影'
    fileRename(root)