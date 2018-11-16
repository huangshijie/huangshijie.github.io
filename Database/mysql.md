# Mysql

## Intallation
- Create a file named 'my.ini', this file is used to define properties during mysql service runs.
- Delete 'data' folder if it exits, and recreate a new folder named as 'data'. This folder is uesd to sotre database source.
- Run cmd in administrator model, use commant 'mysqld --initialize', 'mysqld install', 'net start mysql' to finish mysql installation

## Issue
- After instaillation, first time to login mysql needs password. But during installation I did not provided any password.
````
It seems mysql will set a default password during installation. And I found it in file named 'DESKTOP-N9FMTL8.err' in folder 'data'. It'll be recorded automatically in the log file.
````
And first time to login mysql, database require you to use ALTER USER to reset password.
```
ALTER USER user() IDENTIFIED BY 'Abcd1234';
```