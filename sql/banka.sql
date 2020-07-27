CREATE USER IF NOT EXISTS userb IDENTIFIED BY 'pass';

DROP DATABASE IF EXISTS banka;
CREATE DATABASE banka DEFAULT CHARACTER SET utf8;

USE banka;

GRANT ALL ON banka.* TO 'userb'@'%';

FLUSH PRIVILEGES;