CREATE TABLE table_name
(
  id int(11) PRIMARY KEY NOT NULL,
  user_open_id varchar(64) COMMENT '用户中心的唯一id',
  user_amount decimal(10,5),
  creat_time datetime,
  update_time datetime,
  pay_password varchar(64),
  is_open int(11) COMMENT '0:代表未开启支付密码,1:代表开启支付密码',
  check_key varchar(64) COMMENT '平台进行用户余额更改时,首先校验key值,否则无法进行更改操作',
  version int(11) COMMENT '基于MySQL乐观锁,解决并发访问'
);
CREATE TABLE user
(
  userId int,
  userName varchar(64),
  name varchar(64) COMMENT '昵称',
  password varchar(128),
  salt varchar(64) COMMENT '加密密码的盐',
  state int COMMENT '用户状态',
  createTime datetime,
  expiredDate datetime COMMENT '过期时间',
  roleid int
);
CREATE TABLE SysRole
(
  roleid int PRIMARY KEY NOT NULL,
  role varchar(64),
  description varchar(128) COMMENT '角色描述',
  available boolean
);
CREATE UNIQUE INDEX SysRole_roleid_uindex ON SysRole (roleid);
CREATE TABLE SysPermission
(
  permissionId int PRIMARY KEY NOT NULL,
  permissionName varchar(64),
  resourceType varchar(64),
  url varchar(128),
  permission varchar(64) COMMENT '权限字符串',
  parentId varchar(64),
  parentids varchar(64),
  available int
);
CREATE UNIQUE INDEX SysPermission_permissionId_uindex ON SysPermission (permissionId);