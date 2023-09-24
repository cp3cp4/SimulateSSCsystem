CREATE TABLE `product` (
  `id` varchar(36) NOT NULL,
  `productNum` varchar(50) NOT NULL,
  `productName` varchar(50) DEFAULT NULL,
  `cityName` varchar(50) DEFAULT NULL,
  `DepartureTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `productPrice` double DEFAULT NULL,
  `productDesc` varchar(500) DEFAULT NULL,
  `productStatus` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `product` (`id`,`productNum`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TRIGGER `product_before_insert` BEFORE INSERT ON `product` FOR EACH ROW
BEGIN
IF new.id is NULL THEN
		SET new.id = UUID();
END IF;
END;



insert into PRODUCT (id, productnum, productname, cityname, departuretime, productprice, productdesc, productstatus)
values ('676C5BD1D35E429A8C2E114939C5685A', 'itcast-002', '北京三日游', '北京', '10-10-2018 10:10:00.000000', 1200, '不错的旅行', 1);
insert into PRODUCT (id, productnum, productname, cityname, departuretime, productprice, productdesc, productstatus)
values ('12B7ABF2A4C544568B0A7C69F36BF8B7', 'itcast-003', '上海五日游', '上海', '25-04-2018 14:30:00.000000', 1800, '魔都我来了', 0);
insert into PRODUCT (id, productnum, productname, cityname, departuretime, productprice, productdesc, productstatus)
values ('9F71F01CB448476DAFB309AA6DF9497F', 'itcast-001', '北京三日游', '北京', '10-10-2018 10:10:00.000000', 1200, '不错的旅行', 1);






CREATE TABLE member(
       id varchar(32) ,
       name VARCHAR(20),
       nickname VARCHAR(20),
       phoneNum VARCHAR(20),
       email VARCHAR(20),
			 PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

insert into MEMBER (id, name, nickname, phonenum, email)
values ('E61D65F673D54F68B0861025C69773DB', '张三', '小三', '18888888888', 'zs@163.com');



CREATE TABLE orders(
  id varchar(32) NOT NUll,
  orderNum VARCHAR(20) NOT NULL,
  orderTime timestamp,
  peopleCount INT,
  orderDesc VARCHAR(500),
  payType INT,
  orderStatus INT,
  productId varchar(36)NOT NULL,
  memberId varchar(32),
	PRIMARY KEY (`id`),
	UNIQUE KEY `orders` (`orderNum`,`productId`),
  FOREIGN KEY (productId) REFERENCES product(id),
  FOREIGN KEY (memberId) REFERENCES member(id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;



CREATE TRIGGER `orders_before_insert` BEFORE INSERT ON `orders` FOR EACH ROW
BEGIN
IF new.id is NULL THEN
		SET new.id = UUID();
END IF;
END;

CREATE TRIGGER `member_before_insert` BEFORE INSERT ON `member` FOR EACH ROW
BEGIN
IF new.id is NULL THEN
		SET new.id = UUID();
END IF;
END;


insert into ORDERS (id, ordernum, ordertime, peoplecount, orderdesc, paytype, orderstatus,
productid, memberid)
values ('0E7231DC797C486290E8713CA3C6ECCC', '12345', null, 2, '没什么', 0, 1, '676C5BD1D35E429A8C2E114939C5685A','E61D65F673D54F68B0861025C69773DB');
insert into ORDERS (id, ordernum, ordertime, peoplecount, orderdesc, paytype, orderstatus, productid, memberid)
values ('5DC6A48DD4E94592AE904930EA866AFA', '54321', null, 2, '没什么', 0, 1, '676C5BD1D35E429A8C2E114939C5685A', 'E61D65F673D54F68B0861025C69773DB');
insert into ORDERS (id, ordernum, ordertime, peoplecount, orderdesc, paytype, orderstatus, productid, memberid)
values ('2FF351C4AC744E2092DCF08CFD314420', '67890',null, 2, '没什么', 0, 1, '12B7ABF2A4C544568B0A7C69F36BF8B7', 'E61D65F673D54F68B0861025C69773DB');
insert into ORDERS (id, ordernum, ordertime, peoplecount, orderdesc, paytype, orderstatus, productid, memberid)
values ('A0657832D93E4B10AE88A2D4B70B1A28', '98765',null, 2, '没什么', 0, 1, '12B7ABF2A4C544568B0A7C69F36BF8B7', 'E61D65F673D54F68B0861025C69773DB');
insert into ORDERS (id, ordernum, ordertime, peoplecount, orderdesc, paytype, orderstatus, productid, memberid)
values ('E4DD4C45EED84870ABA83574A801083E', '11111', null, 2, '没什么', 0, 1, '12B7ABF2A4C544568B0A7C69F36BF8B7', 'E61D65F673D54F68B0861025C69773DB');
insert into ORDERS (id, ordernum, ordertime, peoplecount, orderdesc, paytype, orderstatus, productid, memberid)
values ('96CC8BD43C734CC2ACBFF09501B4DD5D', '22222', null, 2, '没什么', 0, 1, '12B7ABF2A4C544568B0A7C69F36BF8B7', 'E61D65F673D54F68B0861025C69773DB');
insert into ORDERS (id, ordernum, ordertime, peoplecount, orderdesc, paytype, orderstatus, productid, memberid)
values ('55F9AF582D5A4DB28FB4EC3199385762', '33333',null, 2, '没什么', 0, 1, '9F71F01CB448476DAFB309AA6DF9497F', 'E61D65F673D54F68B0861025C69773DB');
insert into ORDERS (id, ordernum, ordertime, peoplecount, orderdesc, paytype, orderstatus, productid, memberid)
values ('CA005CF1BE3C4EF68F88ABC7DF30E976', '44444', null, 2, '没什么', 0, 1, '9F71F01CB448476DAFB309AA6DF9497F', 'E61D65F673D54F68B0861025C69773DB');
insert into ORDERS (id, ordernum, ordertime, peoplecount, orderdesc, paytype, orderstatus, productid, memberid)
values ('3081770BC3984EF092D9E99760FDABDE', '55555', null, 2, '没什么', 0, 1, '9F71F01CB448476DAFB309AA6DF9497F', 'E61D65F673D54F68B0861025C69773DB');

CREATE TABLE traveller(
           id varchar(32),
           NAME VARCHAR(20),
           sex VARCHAR(20),
           phoneNum VARCHAR(20),
           credentialsType INT,
           credentialsNum VARCHAR(50),
           travellerType INT,
           PRIMARY KEY (`id`)
       )ENGINE=InnoDB DEFAULT CHARSET=utf8;

       CREATE TRIGGER `traveller_before_insert` BEFORE INSERT ON `traveller` FOR EACH ROW
       BEGIN
       IF new.id is NULL THEN
       		SET new.id = UUID();
       END IF;
       END;


       insert into TRAVELLER (id, name, sex, phonenum, credentialstype, credentialsnum, travellertype) values ('3FE27DF2A4E44A6DBC5D0FE4651D3D3E', '张龙', '男', '13333333333', 0, '123456789009876543', 0);
       insert into TRAVELLER (id, name, sex, phonenum, credentialstype, credentialsnum, travellertype) values ('EE7A71FB6945483FBF91543DBE851960', '张小龙', '男', '15555555555', 0, '987654321123456789', 1);


CREATE TABLE order_traveller(
    orderId varchar(32),
    travellerId varchar(32),
    PRIMARY KEY (orderId,travellerId),
    FOREIGN KEY (orderId) REFERENCES orders(id),
    FOREIGN KEY (travellerId) REFERENCES traveller(id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;
insert into ORDER_TRAVELLER (orderid, travellerid)
values ('0E7231DC797C486290E8713CA3C6ECCC', '3FE27DF2A4E44A6DBC5D0FE4651D3D3E'); insert into ORDER_TRAVELLER (orderid, travellerid)
values ('2FF351C4AC744E2092DCF08CFD314420', '3FE27DF2A4E44A6DBC5D0FE4651D3D3E'); insert into ORDER_TRAVELLER (orderid, travellerid)
values ('3081770BC3984EF092D9E99760FDABDE', 'EE7A71FB6945483FBF91543DBE851960'); insert into ORDER_TRAVELLER (orderid, travellerid)
values ('55F9AF582D5A4DB28FB4EC3199385762', 'EE7A71FB6945483FBF91543DBE851960'); insert into ORDER_TRAVELLER (orderid, travellerid)
values ('5DC6A48DD4E94592AE904930EA866AFA', '3FE27DF2A4E44A6DBC5D0FE4651D3D3E'); insert into ORDER_TRAVELLER (orderid, travellerid)
values ('96CC8BD43C734CC2ACBFF09501B4DD5D', 'EE7A71FB6945483FBF91543DBE851960'); insert into ORDER_TRAVELLER (orderid, travellerid)
values ('A0657832D93E4B10AE88A2D4B70B1A28', '3FE27DF2A4E44A6DBC5D0FE4651D3D3E'); insert into ORDER_TRAVELLER (orderid, travellerid)
values ('CA005CF1BE3C4EF68F88ABC7DF30E976', 'EE7A71FB6945483FBF91543DBE851960'); insert into ORDER_TRAVELLER (orderid, travellerid)
values ('E4DD4C45EED84870ABA83574A801083E', 'EE7A71FB6945483FBF91543DBE851960');



CREATE TABLE users(
    id varchar(32),
    email VARCHAR(50) NOT NULL,
    username VARCHAR(50),
    PASSWORD VARCHAR(50),
    phoneNum VARCHAR(20),
    STATUS INT,
    PRIMARY KEY (`id`),
     UNIQUE KEY `users` (`email`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TRIGGER `users_before_insert` BEFORE INSERT ON `users` FOR EACH ROW
BEGIN
IF new.id is NULL THEN
		SET new.id = UUID();
END IF;
END;


CREATE TABLE role(
    id varchar(32),
    roleName VARCHAR(50) ,
    roleDesc VARCHAR(50),
    PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TRIGGER `role_before_insert` BEFORE INSERT ON `role` FOR EACH ROW
BEGIN
IF new.id is NULL THEN
		SET new.id = UUID();
END IF;
END;

CREATE TABLE users_role(
    userId varchar(32),
    roleId varchar(32),
    PRIMARY KEY(userId,roleId),
    FOREIGN KEY (userId) REFERENCES users(id),
    FOREIGN KEY (roleId) REFERENCES role(id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

 CREATE TABLE permission(
    id varchar(32),
    permissionName VARCHAR(50) ,
    url VARCHAR(50),
    PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TRIGGER `permission_before_insert` BEFORE INSERT ON `permission` FOR EACH ROW
BEGIN
IF new.id is NULL THEN
		SET new.id = UUID();
END IF;
END;

 CREATE TABLE role_permission(
    permissionId varchar(32),
    roleId varchar(32),
    PRIMARY KEY(permissionId,roleId),
    FOREIGN KEY (permissionId) REFERENCES permission(id),
    FOREIGN KEY (roleId) REFERENCES role(id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;