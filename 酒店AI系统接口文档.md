# 酒店AI系统接口文档

## 功能接口

#### 用户登录

接口地址：localhost:8088/hotel/users/login

请求参数：

| 参数     | 是否必填 | 类型   | 说明   |
| -------- | -------- | ------ | ------ |
| username | 是       | String | 用户名 |
| password | 是       | String | 密码   |

返回参数：

| 参数  | 类型   | 说明                                 |
| ----- | ------ | ------------------------------------ |
| msg   | String | 返回信息                             |
| data  | Object | 返回数据                             |
| code  | int    | 状态码(0=成功，500=失败,1000=无权限) |
| count | int    | 返回数据条数                         |

#### 用户登出

接口地址：localhost:8088/hotel/users/logout

请求参数：无

返回参数：

| 参数  | 类型   | 说明                                 |
| ----- | ------ | ------------------------------------ |
| msg   | String | 返回信息                             |
| data  | Object | 返回数据                             |
| code  | int    | 状态码(0=成功，500=失败,1000=无权限) |
| count | int    | 返回数据条数                         |

#### 用户注册

## 1.用户接口

#### 添加用户

接口地址：localhost:8088/hotel/users/insertUser

请求参数：

| 参数     | 是否必填 | 类型          | 说明     |
| -------- | -------- | ------------- | -------- |
| username | 是       | String        | 用户名   |
| password | 是       | String        | 密码     |
| avatar   | 是       | MultipartFile | 头像     |
| nickname | 否       | String        | 昵称     |
| truename | 否       | String        | 真实姓名 |
| gender   | 否       | String        | 性别     |
| nation   | 否       | String        | 民族     |
| address  | 否       | String        | 住址     |
| birthday | 否       | Date          | 生日     |
| phone    | 否       | String        | 电话     |
| email    | 否       | String        | 电子邮箱 |

返回参数：

| 参数  | 类型   | 说明                                 |
| ----- | ------ | ------------------------------------ |
| msg   | String | 返回信息                             |
| data  | Object | 返回数据                             |
| code  | int    | 状态码(0=成功，500=失败,1000=无权限) |
| count | int    | 返回数据条数                         |

#### 修改用户

接口地址：localhost:8088/hotel/users/updateUser

请求参数：

| 参数     | 是否必填 | 类型          | 说明     |
| -------- | -------- | ------------- | -------- |
| username | 是       | String        | 用户名   |
| password | 是       | String        | 密码     |
| avatar   | 是       | MultipartFile | 头像     |
| nickname | 否       | String        | 昵称     |
| truename | 否       | String        | 真实姓名 |
| gender   | 否       | String        | 性别     |
| nation   | 否       | String        | 民族     |
| address  | 否       | String        | 住址     |
| birthday | 否       | Date          | 生日     |
| phone    | 否       | String        | 电话     |
| email    | 否       | String        | 电子邮箱 |

返回参数：

| 参数  | 类型   | 说明                                 |
| ----- | ------ | ------------------------------------ |
| msg   | String | 返回信息                             |
| data  | Object | 返回数据                             |
| code  | int    | 状态码(0=成功，500=失败,1000=无权限) |
| count | int    | 返回数据条数                         |

#### 根据id回收用户

接口地址：localhost:8088/hotel/users/recycleUser

请求参数：

| 参数 | 是否必填 | 类型 | 说明   |
| ---- | -------- | ---- | ------ |
| id   | 是       | int  | 用户id |

返回参数：

| 参数  | 类型   | 说明                                 |
| ----- | ------ | ------------------------------------ |
| msg   | String | 返回信息                             |
| data  | Object | 返回数据                             |
| code  | int    | 状态码(0=成功，500=失败,1000=无权限) |
| count | int    | 返回数据条数                         |

#### 将回收的用户恢复到正常状态

接口地址：localhost:8088/hotel/users/recoveryRecycleUser

请求参数：

| 参数 | 是否必填 | 类型 | 说明   |
| ---- | -------- | ---- | ------ |
| id   | 是       | int  | 用户id |

返回参数：

| 参数  | 类型   | 说明                                 |
| ----- | ------ | ------------------------------------ |
| msg   | String | 返回信息                             |
| data  | Object | 返回数据                             |
| code  | int    | 状态码(0=成功，500=失败,1000=无权限) |
| count | int    | 返回数据条数                         |

#### 根据id将用户拉入黑名单

接口地址：localhost:8088/hotel/users/lockingUser

请求参数：

| 参数 | 是否必填 | 类型 | 说明   |
| ---- | -------- | ---- | ------ |
| id   | 是       | int  | 用户id |

返回参数：

| 参数  | 类型   | 说明                                 |
| ----- | ------ | ------------------------------------ |
| msg   | String | 返回信息                             |
| data  | Object | 返回数据                             |
| code  | int    | 状态码(0=成功，500=失败,1000=无权限) |
| count | int    | 返回数据条数                         |

#### 将黑名单的用户恢复到正常状态

接口地址：localhost:8088/hotel/users/recoveryLockingUser

请求参数：

| 参数 | 是否必填 | 类型 | 说明   |
| ---- | -------- | ---- | ------ |
| id   | 是       | int  | 用户id |

返回参数：

| 参数  | 类型   | 说明                                 |
| ----- | ------ | ------------------------------------ |
| msg   | String | 返回信息                             |
| data  | Object | 返回数据                             |
| code  | int    | 状态码(0=成功，500=失败,1000=无权限) |
| count | int    | 返回数据条数                         |

#### 根据id删除用户

接口地址：localhost:8088/hotel/users/deleteUser

请求参数：

| 参数 | 是否必填 | 类型 | 说明   |
| ---- | -------- | ---- | ------ |
| id   | 是       | int  | 用户id |

返回参数：

| 参数  | 类型   | 说明                                 |
| ----- | ------ | ------------------------------------ |
| msg   | String | 返回信息                             |
| data  | Object | 返回数据                             |
| code  | int    | 状态码(0=成功，500=失败,1000=无权限) |
| count | int    | 返回数据条数                         |

#### 根据id查询用户

接口地址：localhost:8088/hotel/select/getUserById

请求参数：

| 参数 | 是否必填 | 类型 | 说明   |
| ---- | -------- | ---- | ------ |
| id   | 是       | int  | 用户id |

返回参数：

| 参数                         | 类型   | 说明                                        |
| ---------------------------- | ------ | ------------------------------------------- |
| id                           | int    | 用户id                                      |
| username                     | String | 用户名                                      |
| password                     | String | 密码                                        |
| nickname                     | String | 昵称                                        |
| avatar                       | String | 头像                                        |
| truename                     | String | 真实姓名                                    |
| gender                       | String | 性别                                        |
| nation                       | String | 民族                                        |
| address                      | String | 地址                                        |
| birthday                     | Date   | 出生日期                                    |
| phone                        | String | 绑定手机                                    |
| email                        | String | 电子邮箱                                    |
| microblogAccountStates       | int    | 关联微博账号(0=未关联,1=已关联)             |
| qqAccountStates              | int    | 关联qq账号(0=未关联,1=已关联)               |
| wechatAccountStates          | int    | 关联微信账号(0=未关联,1=已关联)             |
| alipayAccountStates          | int    | 关联支付宝账号(0=未关联,1=已关联)           |
| baiduAccountStates           | int    | 关联百度账号(0=未关联,1=已关联)             |
| idcardFront                  | String | 身份证正面                                  |
| idcardBack                   | String | 身份证背面                                  |
| idcardNumber                 | String | 身份证号                                    |
| registrationDate             | Date   | 注册时间                                    |
| membershipScore              | String | 会员积分                                    |
| membershipLevel              | int    | 会员等级                                    |
| realnameAuthenticationStates | int    | 实名认证(0=未认证,1=已认证)                 |
| states                       | int    | 用户状态(1=正常,2=删除,3=锁定,999=隐藏账户) |

| 参数  | 类型   | 说明                                 |
| ----- | ------ | ------------------------------------ |
| msg   | String | 返回信息                             |
| data  | Object | 返回数据                             |
| code  | int    | 状态码(0=成功，500=失败,1000=无权限) |
| count | int    | 返回数据条数                         |

#### 根据用户名查询用户

接口地址：localhost:8088/hotel/select/getUserByUsername

请求参数：

| 参数     | 是否必填 | 类型   | 说明   |
| -------- | -------- | ------ | ------ |
| username | 是       | String | 用户名 |

返回参数：

| 参数                         | 类型   | 说明                                        |
| ---------------------------- | ------ | ------------------------------------------- |
| id                           | int    | 用户id                                      |
| username                     | String | 用户名                                      |
| password                     | String | 密码                                        |
| nickname                     | String | 昵称                                        |
| avatar                       | String | 头像                                        |
| truename                     | String | 真实姓名                                    |
| gender                       | String | 性别                                        |
| nation                       | String | 民族                                        |
| address                      | String | 地址                                        |
| birthday                     | Date   | 出生日期                                    |
| phone                        | String | 绑定手机                                    |
| email                        | String | 电子邮箱                                    |
| microblogAccountStates       | int    | 关联微博账号(0=未关联,1=已关联)             |
| qqAccountStates              | int    | 关联qq账号(0=未关联,1=已关联)               |
| wechatAccountStates          | int    | 关联微信账号(0=未关联,1=已关联)             |
| alipayAccountStates          | int    | 关联支付宝账号(0=未关联,1=已关联)           |
| baiduAccountStates           | int    | 关联百度账号(0=未关联,1=已关联)             |
| idcardFront                  | String | 身份证正面                                  |
| idcardBack                   | String | 身份证背面                                  |
| idcardNumber                 | String | 身份证号                                    |
| registrationDate             | Date   | 注册时间                                    |
| membershipScore              | String | 会员积分                                    |
| membershipLevel              | int    | 会员等级                                    |
| realnameAuthenticationStates | int    | 实名认证(0=未认证,1=已认证)                 |
| states                       | int    | 用户状态(1=正常,2=删除,3=锁定,999=隐藏账户) |

| 参数  | 类型   | 说明                                 |
| ----- | ------ | ------------------------------------ |
| msg   | String | 返回信息                             |
| data  | Object | 返回数据                             |
| code  | int    | 状态码(0=成功，500=失败,1000=无权限) |
| count | int    | 返回数据条数                         |

#### 获取正常状态的用户分页列表

接口地址：localhost:8088/hotel/select/getUserList

请求参数：

| 参数  | 是否必填 | 类型 | 说明     |
| ----- | -------- | ---- | -------- |
| page  | 是       | int  | 当前页数 |
| limit | 是       | int  | 每页条数 |

返回参数：

| 参数                         | 类型   | 说明                                        |
| ---------------------------- | ------ | ------------------------------------------- |
| id                           | int    | 用户id                                      |
| username                     | String | 用户名                                      |
| password                     | String | 密码                                        |
| nickname                     | String | 昵称                                        |
| avatar                       | String | 头像                                        |
| truename                     | String | 真实姓名                                    |
| gender                       | String | 性别                                        |
| nation                       | String | 民族                                        |
| address                      | String | 地址                                        |
| birthday                     | Date   | 出生日期                                    |
| phone                        | String | 绑定手机                                    |
| email                        | String | 电子邮箱                                    |
| microblogAccountStates       | int    | 关联微博账号(0=未关联,1=已关联)             |
| qqAccountStates              | int    | 关联qq账号(0=未关联,1=已关联)               |
| wechatAccountStates          | int    | 关联微信账号(0=未关联,1=已关联)             |
| alipayAccountStates          | int    | 关联支付宝账号(0=未关联,1=已关联)           |
| baiduAccountStates           | int    | 关联百度账号(0=未关联,1=已关联)             |
| idcardFront                  | String | 身份证正面                                  |
| idcardBack                   | String | 身份证背面                                  |
| idcardNumber                 | String | 身份证号                                    |
| registrationDate             | Date   | 注册时间                                    |
| membershipScore              | String | 会员积分                                    |
| membershipLevel              | int    | 会员等级                                    |
| realnameAuthenticationStates | int    | 实名认证(0=未认证,1=已认证)                 |
| states                       | int    | 用户状态(1=正常,2=删除,3=锁定,999=隐藏账户) |

| 参数  | 类型   | 说明                                 |
| ----- | ------ | ------------------------------------ |
| msg   | String | 返回信息                             |
| data  | Object | 返回数据                             |
| code  | int    | 状态码(0=成功，500=失败,1000=无权限) |
| count | int    | 返回数据条数                         |

#### 获取回收的用户分页列表

接口地址：localhost:8088/hotel/select/getRecycleUserList

请求参数：

| 参数  | 是否必填 | 类型 | 说明     |
| ----- | -------- | ---- | -------- |
| page  | 是       | int  | 当前页数 |
| limit | 是       | int  | 每页条数 |

返回参数：

| 参数                         | 类型   | 说明                                        |
| ---------------------------- | ------ | ------------------------------------------- |
| id                           | int    | 用户id                                      |
| username                     | String | 用户名                                      |
| password                     | String | 密码                                        |
| nickname                     | String | 昵称                                        |
| avatar                       | String | 头像                                        |
| truename                     | String | 真实姓名                                    |
| gender                       | String | 性别                                        |
| nation                       | String | 民族                                        |
| address                      | String | 地址                                        |
| birthday                     | Date   | 出生日期                                    |
| phone                        | String | 绑定手机                                    |
| email                        | String | 电子邮箱                                    |
| microblogAccountStates       | int    | 关联微博账号(0=未关联,1=已关联)             |
| qqAccountStates              | int    | 关联qq账号(0=未关联,1=已关联)               |
| wechatAccountStates          | int    | 关联微信账号(0=未关联,1=已关联)             |
| alipayAccountStates          | int    | 关联支付宝账号(0=未关联,1=已关联)           |
| baiduAccountStates           | int    | 关联百度账号(0=未关联,1=已关联)             |
| idcardFront                  | String | 身份证正面                                  |
| idcardBack                   | String | 身份证背面                                  |
| idcardNumber                 | String | 身份证号                                    |
| registrationDate             | Date   | 注册时间                                    |
| membershipScore              | String | 会员积分                                    |
| membershipLevel              | int    | 会员等级                                    |
| realnameAuthenticationStates | int    | 实名认证(0=未认证,1=已认证)                 |
| states                       | int    | 用户状态(1=正常,2=删除,3=锁定,999=隐藏账户) |

| 参数  | 类型   | 说明                                 |
| ----- | ------ | ------------------------------------ |
| msg   | String | 返回信息                             |
| data  | Object | 返回数据                             |
| code  | int    | 状态码(0=成功，500=失败,1000=无权限) |
| count | int    | 返回数据条数                         |

#### 获取黑名单的用户分页列表

接口地址：localhost:8088/hotel/select/getLockingUserList

请求参数：

| 参数  | 是否必填 | 类型 | 说明     |
| ----- | -------- | ---- | -------- |
| page  | 是       | int  | 当前页数 |
| limit | 是       | int  | 每页条数 |

返回参数：

| 参数                         | 类型   | 说明                                        |
| ---------------------------- | ------ | ------------------------------------------- |
| id                           | int    | 用户id                                      |
| username                     | String | 用户名                                      |
| password                     | String | 密码                                        |
| nickname                     | String | 昵称                                        |
| avatar                       | String | 头像                                        |
| truename                     | String | 真实姓名                                    |
| gender                       | String | 性别                                        |
| nation                       | String | 民族                                        |
| address                      | String | 地址                                        |
| birthday                     | Date   | 出生日期                                    |
| phone                        | String | 绑定手机                                    |
| email                        | String | 电子邮箱                                    |
| microblogAccountStates       | int    | 关联微博账号(0=未关联,1=已关联)             |
| qqAccountStates              | int    | 关联qq账号(0=未关联,1=已关联)               |
| wechatAccountStates          | int    | 关联微信账号(0=未关联,1=已关联)             |
| alipayAccountStates          | int    | 关联支付宝账号(0=未关联,1=已关联)           |
| baiduAccountStates           | int    | 关联百度账号(0=未关联,1=已关联)             |
| idcardFront                  | String | 身份证正面                                  |
| idcardBack                   | String | 身份证背面                                  |
| idcardNumber                 | String | 身份证号                                    |
| registrationDate             | Date   | 注册时间                                    |
| membershipScore              | String | 会员积分                                    |
| membershipLevel              | int    | 会员等级                                    |
| realnameAuthenticationStates | int    | 实名认证(0=未认证,1=已认证)                 |
| states                       | int    | 用户状态(1=正常,2=删除,3=锁定,999=隐藏账户) |

| 参数  | 类型   | 说明                                 |
| ----- | ------ | ------------------------------------ |
| msg   | String | 返回信息                             |
| data  | Object | 返回数据                             |
| code  | int    | 状态码(0=成功，500=失败,1000=无权限) |
| count | int    | 返回数据条数                         |

#### 搜索用户（模糊查询）

接口地址：localhost:8088/hotel/select/searchUser

请求参数：

| 参数     | 是否必填                       | 类型   | 说明     |
| -------- | ------------------------------ | ------ | -------- |
| username | 至少有一项必填，也可以三项都填 | String | 用户名   |
| phone    | 至少有一项必填，也可以三项都填 | String | 电话号码 |
| email    | 至少有一项必填，也可以三项都填 | String | 电子邮箱 |

返回参数：

| 参数                         | 类型   | 说明                                        |
| ---------------------------- | ------ | ------------------------------------------- |
| id                           | int    | 用户id                                      |
| username                     | String | 用户名                                      |
| password                     | String | 密码                                        |
| nickname                     | String | 昵称                                        |
| avatar                       | String | 头像                                        |
| truename                     | String | 真实姓名                                    |
| gender                       | String | 性别                                        |
| nation                       | String | 民族                                        |
| address                      | String | 地址                                        |
| birthday                     | Date   | 出生日期                                    |
| phone                        | String | 绑定手机                                    |
| email                        | String | 电子邮箱                                    |
| microblogAccountStates       | int    | 关联微博账号(0=未关联,1=已关联)             |
| qqAccountStates              | int    | 关联qq账号(0=未关联,1=已关联)               |
| wechatAccountStates          | int    | 关联微信账号(0=未关联,1=已关联)             |
| alipayAccountStates          | int    | 关联支付宝账号(0=未关联,1=已关联)           |
| baiduAccountStates           | int    | 关联百度账号(0=未关联,1=已关联)             |
| idcardFront                  | String | 身份证正面                                  |
| idcardBack                   | String | 身份证背面                                  |
| idcardNumber                 | String | 身份证号                                    |
| registrationDate             | Date   | 注册时间                                    |
| membershipScore              | String | 会员积分                                    |
| membershipLevel              | int    | 会员等级                                    |
| realnameAuthenticationStates | int    | 实名认证(0=未认证,1=已认证)                 |
| states                       | int    | 用户状态(1=正常,2=删除,3=锁定,999=隐藏账户) |

| 参数  | 类型   | 说明                                 |
| ----- | ------ | ------------------------------------ |
| msg   | String | 返回信息                             |
| data  | Object | 返回数据                             |
| code  | int    | 状态码(0=成功，500=失败,1000=无权限) |
| count | int    | 返回数据条数                         |

#### 根据会员等级降序排序

接口地址：localhost:8088/hotel/select/getUserListByMembershipLevelSortDesc

请求参数：

| 参数  | 是否必填 | 类型 | 说明     |
| ----- | -------- | ---- | -------- |
| page  | 是       | int  | 当前页数 |
| limit | 是       | int  | 每页条数 |

返回参数：

| 参数                         | 类型   | 说明                                        |
| ---------------------------- | ------ | ------------------------------------------- |
| id                           | int    | 用户id                                      |
| username                     | String | 用户名                                      |
| password                     | String | 密码                                        |
| nickname                     | String | 昵称                                        |
| avatar                       | String | 头像                                        |
| truename                     | String | 真实姓名                                    |
| gender                       | String | 性别                                        |
| nation                       | String | 民族                                        |
| address                      | String | 地址                                        |
| birthday                     | Date   | 出生日期                                    |
| phone                        | String | 绑定手机                                    |
| email                        | String | 电子邮箱                                    |
| microblogAccountStates       | int    | 关联微博账号(0=未关联,1=已关联)             |
| qqAccountStates              | int    | 关联qq账号(0=未关联,1=已关联)               |
| wechatAccountStates          | int    | 关联微信账号(0=未关联,1=已关联)             |
| alipayAccountStates          | int    | 关联支付宝账号(0=未关联,1=已关联)           |
| baiduAccountStates           | int    | 关联百度账号(0=未关联,1=已关联)             |
| idcardFront                  | String | 身份证正面                                  |
| idcardBack                   | String | 身份证背面                                  |
| idcardNumber                 | String | 身份证号                                    |
| registrationDate             | Date   | 注册时间                                    |
| membershipScore              | String | 会员积分                                    |
| membershipLevel              | int    | 会员等级                                    |
| realnameAuthenticationStates | int    | 实名认证(0=未认证,1=已认证)                 |
| states                       | int    | 用户状态(1=正常,2=删除,3=锁定,999=隐藏账户) |

| 参数  | 类型   | 说明                                 |
| ----- | ------ | ------------------------------------ |
| msg   | String | 返回信息                             |
| data  | Object | 返回数据                             |
| code  | int    | 状态码(0=成功，500=失败,1000=无权限) |
| count | int    | 返回数据条数                         |

#### 根据会员等级升序排序

接口地址：localhost:8088/hotel/select/UserListByMembershipLevelSortAsc

请求参数：

| 参数  | 是否必填 | 类型 | 说明     |
| ----- | -------- | ---- | -------- |
| page  | 是       | int  | 当前页数 |
| limit | 是       | int  | 每页条数 |

返回参数：

| 参数                         | 类型   | 说明                                        |
| ---------------------------- | ------ | ------------------------------------------- |
| id                           | int    | 用户id                                      |
| username                     | String | 用户名                                      |
| password                     | String | 密码                                        |
| nickname                     | String | 昵称                                        |
| avatar                       | String | 头像                                        |
| truename                     | String | 真实姓名                                    |
| gender                       | String | 性别                                        |
| nation                       | String | 民族                                        |
| address                      | String | 地址                                        |
| birthday                     | Date   | 出生日期                                    |
| phone                        | String | 绑定手机                                    |
| email                        | String | 电子邮箱                                    |
| microblogAccountStates       | int    | 关联微博账号(0=未关联,1=已关联)             |
| qqAccountStates              | int    | 关联qq账号(0=未关联,1=已关联)               |
| wechatAccountStates          | int    | 关联微信账号(0=未关联,1=已关联)             |
| alipayAccountStates          | int    | 关联支付宝账号(0=未关联,1=已关联)           |
| baiduAccountStates           | int    | 关联百度账号(0=未关联,1=已关联)             |
| idcardFront                  | String | 身份证正面                                  |
| idcardBack                   | String | 身份证背面                                  |
| idcardNumber                 | String | 身份证号                                    |
| registrationDate             | Date   | 注册时间                                    |
| membershipScore              | String | 会员积分                                    |
| membershipLevel              | int    | 会员等级                                    |
| realnameAuthenticationStates | int    | 实名认证(0=未认证,1=已认证)                 |
| states                       | int    | 用户状态(1=正常,2=删除,3=锁定,999=隐藏账户) |

| 参数  | 类型   | 说明                                 |
| ----- | ------ | ------------------------------------ |
| msg   | String | 返回信息                             |
| data  | Object | 返回数据                             |
| code  | int    | 状态码(0=成功，500=失败,1000=无权限) |
| count | int    | 返回数据条数                         |

#### 根据性别筛选

接口地址：localhost:8088/hotel/select/getUserListByGender

请求参数：

| 参数   | 是否必填 | 类型   | 说明     |
| ------ | -------- | ------ | -------- |
| page   | 是       | int    | 当前页数 |
| limit  | 是       | int    | 每页条数 |
| gender | 是       | String | 性别     |

返回参数：

| 参数                         | 类型   | 说明                                        |
| ---------------------------- | ------ | ------------------------------------------- |
| id                           | int    | 用户id                                      |
| username                     | String | 用户名                                      |
| password                     | String | 密码                                        |
| nickname                     | String | 昵称                                        |
| avatar                       | String | 头像                                        |
| truename                     | String | 真实姓名                                    |
| gender                       | String | 性别                                        |
| nation                       | String | 民族                                        |
| address                      | String | 地址                                        |
| birthday                     | Date   | 出生日期                                    |
| phone                        | String | 绑定手机                                    |
| email                        | String | 电子邮箱                                    |
| microblogAccountStates       | int    | 关联微博账号(0=未关联,1=已关联)             |
| qqAccountStates              | int    | 关联qq账号(0=未关联,1=已关联)               |
| wechatAccountStates          | int    | 关联微信账号(0=未关联,1=已关联)             |
| alipayAccountStates          | int    | 关联支付宝账号(0=未关联,1=已关联)           |
| baiduAccountStates           | int    | 关联百度账号(0=未关联,1=已关联)             |
| idcardFront                  | String | 身份证正面                                  |
| idcardBack                   | String | 身份证背面                                  |
| idcardNumber                 | String | 身份证号                                    |
| registrationDate             | Date   | 注册时间                                    |
| membershipScore              | String | 会员积分                                    |
| membershipLevel              | int    | 会员等级                                    |
| realnameAuthenticationStates | int    | 实名认证(0=未认证,1=已认证)                 |
| states                       | int    | 用户状态(1=正常,2=删除,3=锁定,999=隐藏账户) |

| 参数  | 类型   | 说明                                 |
| ----- | ------ | ------------------------------------ |
| msg   | String | 返回信息                             |
| data  | Object | 返回数据                             |
| code  | int    | 状态码(0=成功，500=失败,1000=无权限) |
| count | int    | 返回数据条数                         |

#### 根据角色筛选

接口地址：localhost:8088/hotel/select/getUserListByRole

请求参数：

| 参数   | 是否必填 | 类型   | 说明     |
| ------ | -------- | ------ | -------- |
| page   | 是       | int    | 当前页数 |
| limit  | 是       | int    | 每页条数 |
| roleId | 是       | String | 角色id   |

返回参数：

| 参数                         | 类型   | 说明                                        |
| ---------------------------- | ------ | ------------------------------------------- |
| id                           | int    | 用户id                                      |
| username                     | String | 用户名                                      |
| password                     | String | 密码                                        |
| nickname                     | String | 昵称                                        |
| avatar                       | String | 头像                                        |
| truename                     | String | 真实姓名                                    |
| gender                       | String | 性别                                        |
| nation                       | String | 民族                                        |
| address                      | String | 地址                                        |
| birthday                     | Date   | 出生日期                                    |
| phone                        | String | 绑定手机                                    |
| email                        | String | 电子邮箱                                    |
| microblogAccountStates       | int    | 关联微博账号(0=未关联,1=已关联)             |
| qqAccountStates              | int    | 关联qq账号(0=未关联,1=已关联)               |
| wechatAccountStates          | int    | 关联微信账号(0=未关联,1=已关联)             |
| alipayAccountStates          | int    | 关联支付宝账号(0=未关联,1=已关联)           |
| baiduAccountStates           | int    | 关联百度账号(0=未关联,1=已关联)             |
| idcardFront                  | String | 身份证正面                                  |
| idcardBack                   | String | 身份证背面                                  |
| idcardNumber                 | String | 身份证号                                    |
| registrationDate             | Date   | 注册时间                                    |
| membershipScore              | String | 会员积分                                    |
| membershipLevel              | int    | 会员等级                                    |
| realnameAuthenticationStates | int    | 实名认证(0=未认证,1=已认证)                 |
| states                       | int    | 用户状态(1=正常,2=删除,3=锁定,999=隐藏账户) |

| 参数  | 类型   | 说明                                 |
| ----- | ------ | ------------------------------------ |
| msg   | String | 返回信息                             |
| data  | Object | 返回数据                             |
| code  | int    | 状态码(0=成功，500=失败,1000=无权限) |
| count | int    | 返回数据条数                         |

#### 批量回收用户

接口地址：localhost:8088/hotel/users/recycleUserBatch

请求参数：

| 参数 | 是否必填 | 类型 | 说明   |
| ---- | -------- | ---- | ------ |
| ids  | 是       | List | id集合 |

返回参数：

| 参数  | 类型   | 说明                                 |
| ----- | ------ | ------------------------------------ |
| msg   | String | 返回信息                             |
| data  | Object | 返回数据                             |
| code  | int    | 状态码(0=成功，500=失败,1000=无权限) |
| count | int    | 返回数据条数                         |

#### 将回收的用户批量恢复到正常状态

接口地址：localhost:8088/hotel/users/recoveryRecycleUserBatch

请求参数：

| 参数 | 是否必填 | 类型 | 说明   |
| ---- | -------- | ---- | ------ |
| ids  | 是       | List | id集合 |

返回参数：

| 参数  | 类型   | 说明                                 |
| ----- | ------ | ------------------------------------ |
| msg   | String | 返回信息                             |
| data  | Object | 返回数据                             |
| code  | int    | 状态码(0=成功，500=失败,1000=无权限) |
| count | int    | 返回数据条数                         |

#### 批量删除用户

接口地址：localhost:8088/hotel/users/deleteUserBatch

请求参数：

| 参数 | 是否必填 | 类型 | 说明   |
| ---- | -------- | ---- | ------ |
| ids  | 是       | List | id集合 |

返回参数：

| 参数  | 类型   | 说明                                 |
| ----- | ------ | ------------------------------------ |
| msg   | String | 返回信息                             |
| data  | Object | 返回数据                             |
| code  | int    | 状态码(0=成功，500=失败,1000=无权限) |
| count | int    | 返回数据条数                         |

## 2.角色接口

#### 添加角色

接口地址：localhost:8088/hotel/role/insertRole

请求参数：

| 参数            | 是否必填 | 类型   | 说明     |
| --------------- | -------- | ------ | -------- |
| roleName        | 是       | String | 角色名称 |
| roleDescription | 是       | String | 角色描述 |

返回参数：

| 参数  | 类型   | 说明                                 |
| ----- | ------ | ------------------------------------ |
| msg   | String | 返回信息                             |
| data  | Object | 返回数据                             |
| code  | int    | 状态码(0=成功，500=失败,1000=无权限) |
| count | int    | 返回数据条数                         |

#### 修改角色

接口地址：localhost:8088/hotel/role/updateRole

请求参数：

| 参数            | 是否必填 | 类型   | 说明     |
| --------------- | -------- | ------ | -------- |
| id              | 是       | int    | 角色id   |
| roleName        | 是       | String | 角色名称 |
| roleDescription | 是       | String | 角色描述 |

返回参数：

| 参数  | 类型   | 说明                                 |
| ----- | ------ | ------------------------------------ |
| msg   | String | 返回信息                             |
| data  | Object | 返回数据                             |
| code  | int    | 状态码(0=成功，500=失败,1000=无权限) |
| count | int    | 返回数据条数                         |

#### 根据id回收角色

接口地址：localhost:8088/hotel/role/recycleRole

请求参数：

| 参数 | 是否必填 | 类型 | 说明   |
| ---- | -------- | ---- | ------ |
| id   | 是       | int  | 角色id |

返回参数：

| 参数  | 类型   | 说明                                 |
| ----- | ------ | ------------------------------------ |
| msg   | String | 返回信息                             |
| data  | Object | 返回数据                             |
| code  | int    | 状态码(0=成功，500=失败,1000=无权限) |
| count | int    | 返回数据条数                         |

#### 将回收的角色恢复到正常状态

接口地址：localhost:8088/hotel/role/recoveryRecycleRole

请求参数：

| 参数 | 是否必填 | 类型 | 说明   |
| ---- | -------- | ---- | ------ |
| id   | 是       | int  | 角色id |

返回参数：

| 参数  | 类型   | 说明                                 |
| ----- | ------ | ------------------------------------ |
| msg   | String | 返回信息                             |
| data  | Object | 返回数据                             |
| code  | int    | 状态码(0=成功，500=失败,1000=无权限) |
| count | int    | 返回数据条数                         |

#### 根据id删除角色

接口地址：localhost:8088/hotel/role/deleteRole

请求参数：

| 参数 | 是否必填 | 类型 | 说明   |
| ---- | -------- | ---- | ------ |
| id   | 是       | int  | 角色id |

返回参数：

| 参数  | 类型   | 说明                                 |
| ----- | ------ | ------------------------------------ |
| msg   | String | 返回信息                             |
| data  | Object | 返回数据                             |
| code  | int    | 状态码(0=成功，500=失败,1000=无权限) |
| count | int    | 返回数据条数                         |

#### 根据id查询角色

接口地址：localhost:8088/hotel/select/getRoleById

请求参数：

| 参数 | 是否必填 | 类型 | 说明   |
| ---- | -------- | ---- | ------ |
| id   | 是       | int  | 角色id |

返回参数：

| 参数            | 类型   | 说明                    |
| --------------- | ------ | ----------------------- |
| id              | int    | 角色id                  |
| roleName        | String | 角色名称                |
| roleDescription | String | 角色描述                |
| roleSort        | int    | 角色排序                |
| roleStates      | int    | 角色状态(1=正常,2=删除) |

| 参数  | 类型   | 说明                                 |
| ----- | ------ | ------------------------------------ |
| msg   | String | 返回信息                             |
| data  | Object | 返回数据                             |
| code  | int    | 状态码(0=成功，500=失败,1000=无权限) |
| count | int    | 返回数据条数                         |

#### 根据用户名获取角色信息

接口地址：localhost:8088/hotel/select/getRoleByUsername

请求参数：

| 参数     | 是否必填 | 类型   | 说明   |
| -------- | -------- | ------ | ------ |
| username | 是       | String | 角色id |

返回参数：

| 参数            | 类型   | 说明                    |
| --------------- | ------ | ----------------------- |
| id              | int    | 角色id                  |
| roleName        | String | 角色名称                |
| roleDescription | String | 角色描述                |
| roleSort        | int    | 角色排序                |
| roleStates      | int    | 角色状态(1=正常,2=删除) |

| 参数  | 类型   | 说明                                 |
| ----- | ------ | ------------------------------------ |
| msg   | String | 返回信息                             |
| data  | Object | 返回数据                             |
| code  | int    | 状态码(0=成功，500=失败,1000=无权限) |
| count | int    | 返回数据条数                         |

#### 获取正常状态的角色分页列表

接口地址：localhost:8088/hotel/select/getRoleList

请求参数：

| 参数  | 是否必填 | 类型 | 说明     |
| ----- | -------- | ---- | -------- |
| page  | 是       | int  | 当前页数 |
| limit | 是       | int  | 每页条数 |

返回参数：

| 参数            | 类型   | 说明                    |
| --------------- | ------ | ----------------------- |
| id              | int    | 角色id                  |
| roleName        | String | 角色名称                |
| roleDescription | String | 角色描述                |
| roleSort        | int    | 角色排序                |
| roleStates      | int    | 角色状态(1=正常,2=删除) |

| 参数  | 类型   | 说明                                 |
| ----- | ------ | ------------------------------------ |
| msg   | String | 返回信息                             |
| data  | Object | 返回数据                             |
| code  | int    | 状态码(0=成功，500=失败,1000=无权限) |
| count | int    | 返回数据条数                         |

#### 获取回收的角色角色分页列表

接口地址：localhost:8088/hotel/select/getRecycleRoleList

请求参数：

| 参数  | 是否必填 | 类型 | 说明     |
| ----- | -------- | ---- | -------- |
| page  | 是       | int  | 当前页数 |
| limit | 是       | int  | 每页条数 |

返回参数：

| 参数            | 类型   | 说明                    |
| --------------- | ------ | ----------------------- |
| id              | int    | 角色id                  |
| roleName        | String | 角色名称                |
| roleDescription | String | 角色描述                |
| roleSort        | int    | 角色排序                |
| roleStates      | int    | 角色状态(1=正常,2=删除) |

| 参数  | 类型   | 说明                                 |
| ----- | ------ | ------------------------------------ |
| msg   | String | 返回信息                             |
| data  | Object | 返回数据                             |
| code  | int    | 状态码(0=成功，500=失败,1000=无权限) |
| count | int    | 返回数据条数                         |

#### 搜索角色（模糊查询）

接口地址：localhost:8088/hotel/select/searchRole

请求参数：

| 参数            | 是否必填                       | 类型   | 说明     |
| --------------- | ------------------------------ | ------ | -------- |
| roleName        | 至少有一项必填，也可以两项都填 | String | 角色名   |
| roleDescription | 至少有一项必填，也可以两项都填 | String | 角色描述 |

返回参数：

| 参数            | 类型   | 说明                    |
| --------------- | ------ | ----------------------- |
| id              | int    | 角色id                  |
| roleName        | String | 角色名称                |
| roleDescription | String | 角色描述                |
| roleSort        | int    | 角色排序                |
| roleStates      | int    | 角色状态(1=正常,2=删除) |

| 参数  | 类型   | 说明                                 |
| ----- | ------ | ------------------------------------ |
| msg   | String | 返回信息                             |
| data  | Object | 返回数据                             |
| code  | int    | 状态码(0=成功，500=失败,1000=无权限) |
| count | int    | 返回数据条数                         |

#### 批量回收角色

接口地址：localhost:8088/hotel/role/recycleRoleBatch

请求参数：

| 参数 | 是否必填 | 类型 | 说明   |
| ---- | -------- | ---- | ------ |
| ids  | 是       | List | id集合 |

返回参数：

| 参数  | 类型   | 说明                                 |
| ----- | ------ | ------------------------------------ |
| msg   | String | 返回信息                             |
| data  | Object | 返回数据                             |
| code  | int    | 状态码(0=成功，500=失败,1000=无权限) |
| count | int    | 返回数据条数                         |

#### 将回收的角色批量恢复到正常状态

接口地址：localhost:8088/hotel/role/recoveryRecycleRoleBatch

请求参数：

| 参数 | 是否必填 | 类型 | 说明   |
| ---- | -------- | ---- | ------ |
| ids  | 是       | List | id集合 |

返回参数：

| 参数  | 类型   | 说明                                 |
| ----- | ------ | ------------------------------------ |
| msg   | String | 返回信息                             |
| data  | Object | 返回数据                             |
| code  | int    | 状态码(0=成功，500=失败,1000=无权限) |
| count | int    | 返回数据条数                         |

#### 批量删除角色

接口地址：localhost:8088/hotel/role/deleteRoleBatch

请求参数：

| 参数 | 是否必填 | 类型 | 说明   |
| ---- | -------- | ---- | ------ |
| ids  | 是       | List | id集合 |

返回参数：

| 参数  | 类型   | 说明                                 |
| ----- | ------ | ------------------------------------ |
| msg   | String | 返回信息                             |
| data  | Object | 返回数据                             |
| code  | int    | 状态码(0=成功，500=失败,1000=无权限) |
| count | int    | 返回数据条数                         |

## 3.权限接口

#### 添加权限

接口地址：localhost:8088/hotel/permission/insertPermission

请求参数：

| 参数               | 是否必填 | 类型   | 说明           |
| ------------------ | -------- | ------ | -------------- |
| permissionName     | 否       | String | 权限名称       |
| permissionDescribe | 否       | String | 权限描述       |
| permissionKey      | 是       | String | 权限路径       |
| permissionValue    | 是       | String | 权限对应的角色 |
| parentSort         | 是       | int    | 权限排序       |

返回参数：

| 参数  | 类型   | 说明                                 |
| ----- | ------ | ------------------------------------ |
| msg   | String | 返回信息                             |
| data  | Object | 返回数据                             |
| code  | int    | 状态码(0=成功，500=失败,1000=无权限) |
| count | int    | 返回数据条数                         |

#### 修改权限

接口地址：localhost:8088/hotel/permission/updatePermission

请求参数：

| 参数               | 是否必填 | 类型   | 说明           |
| ------------------ | -------- | ------ | -------------- |
| id                 | 是       |        |                |
| permissionName     | 否       | String | 权限名称       |
| permissionDescribe | 否       | String | 权限描述       |
| permissionKey      | 是       | String | 权限路径       |
| permissionValue    | 是       | String | 权限对应的角色 |
| parentPermissionId | 是       | int    | 父权限id       |
| parentSort         | 是       | int    | 权限排序       |

返回参数：

| 参数  | 类型   | 说明                                 |
| ----- | ------ | ------------------------------------ |
| msg   | String | 返回信息                             |
| data  | Object | 返回数据                             |
| code  | int    | 状态码(0=成功，500=失败,1000=无权限) |
| count | int    | 返回数据条数                         |

#### 根据id回收权限

接口地址：localhost:8088/hotel/permission/recyclePermission

请求参数：

| 参数 | 是否必填 | 类型 | 说明   |
| ---- | -------- | ---- | ------ |
| id   | 是       | int  | 权限id |

返回参数：

| 参数  | 类型   | 说明                                 |
| ----- | ------ | ------------------------------------ |
| msg   | String | 返回信息                             |
| data  | Object | 返回数据                             |
| code  | int    | 状态码(0=成功，500=失败,1000=无权限) |
| count | int    | 返回数据条数                         |

#### 将回收的权限恢复到正常状态

接口地址：localhost:8088/hotel/permission/recoveryRecyclePermission

请求参数：

| 参数 | 是否必填 | 类型 | 说明   |
| ---- | -------- | ---- | ------ |
| id   | 是       | int  | 权限id |

返回参数：

| 参数  | 类型   | 说明                                 |
| ----- | ------ | ------------------------------------ |
| msg   | String | 返回信息                             |
| data  | Object | 返回数据                             |
| code  | int    | 状态码(0=成功，500=失败,1000=无权限) |
| count | int    | 返回数据条数                         |

#### 根据id删除权限

接口地址：localhost:8088/hotel/permission/deletePermission

请求参数：

| 参数 | 是否必填 | 类型 | 说明   |
| ---- | -------- | ---- | ------ |
| id   | 是       | int  | 权限id |

返回参数：

| 参数  | 类型   | 说明                                 |
| ----- | ------ | ------------------------------------ |
| msg   | String | 返回信息                             |
| data  | Object | 返回数据                             |
| code  | int    | 状态码(0=成功，500=失败,1000=无权限) |
| count | int    | 返回数据条数                         |

#### 根据id查询权限

接口地址：localhost:8088/hotel/select/getPermissionById

请求参数：

| 参数 | 是否必填 | 类型 | 说明   |
| ---- | -------- | ---- | ------ |
| id   | 是       | int  | 权限id |

返回参数：

| 参数               | 类型   | 说明                    |
| ------------------ | ------ | ----------------------- |
| id                 | int    | 权限id                  |
| permissionName     | String | 权限名称                |
| permissionDescribe | String | 权限描述                |
| permissionKey      | String | 权限路径                |
| permissionValue    | String | 权限对应的角色          |
| parentPermissionId | int    | 父菜单id                |
| parentSort         | int    | 权限排序                |
| permissionStates   | int    | 权限状态(1=正常,2=删除) |
| permissions        | Object | 子菜单                  |

| 参数  | 类型   | 说明                                 |
| ----- | ------ | ------------------------------------ |
| msg   | String | 返回信息                             |
| data  | Object | 返回数据                             |
| code  | int    | 状态码(0=成功，500=失败,1000=无权限) |
| count | int    | 返回数据条数                         |

#### 根据角色名查询分级菜单列表

接口地址：localhost:8088/hotel/select/getPermissionByRoleName

请求参数：

| 参数     | 是否必填 | 类型   | 说明     |
| -------- | -------- | ------ | -------- |
| roleName | 是       | String | 角色名称 |

返回参数：

| 参数               | 类型   | 说明                    |
| ------------------ | ------ | ----------------------- |
| id                 | int    | 权限id                  |
| permissionName     | String | 权限名称                |
| permissionDescribe | String | 权限描述                |
| permissionKey      | String | 权限路径                |
| permissionValue    | String | 权限对应的角色          |
| parentPermissionId | int    | 父菜单id                |
| parentSort         | int    | 权限排序                |
| permissionStates   | int    | 权限状态(1=正常,2=删除) |
| permissions        | Object | 子菜单                  |

| 参数  | 类型   | 说明                                 |
| ----- | ------ | ------------------------------------ |
| msg   | String | 返回信息                             |
| data  | Object | 返回数据                             |
| code  | int    | 状态码(0=成功，500=失败,1000=无权限) |
| count | int    | 返回数据条数                         |

#### 获取正常状态的权限分页列表

接口地址：localhost:8088/hotel/select/getPermissionList

请求参数：

| 参数  | 是否必填 | 类型 | 说明     |
| ----- | -------- | ---- | -------- |
| page  | 是       | int  | 当前页数 |
| limit | 是       | int  | 每页条数 |

返回参数：

| 参数               | 类型   | 说明                    |
| ------------------ | ------ | ----------------------- |
| id                 | int    | 权限id                  |
| permissionName     | String | 权限名称                |
| permissionDescribe | String | 权限描述                |
| permissionKey      | String | 权限路径                |
| permissionValue    | String | 权限对应的角色          |
| parentPermissionId | int    | 父菜单id                |
| parentSort         | int    | 权限排序                |
| permissionStates   | int    | 权限状态(1=正常,2=删除) |
| permissions        | Object | 子菜单                  |

| 参数  | 类型   | 说明                                 |
| ----- | ------ | ------------------------------------ |
| msg   | String | 返回信息                             |
| data  | Object | 返回数据                             |
| code  | int    | 状态码(0=成功，500=失败,1000=无权限) |
| count | int    | 返回数据条数                         |

#### 获取回收的权限分页列表

接口地址：localhost:8088/hotel/select/getRecyclePermissionList

请求参数：

| 参数  | 是否必填 | 类型 | 说明     |
| ----- | -------- | ---- | -------- |
| page  | 是       | int  | 当前页数 |
| limit | 是       | int  | 每页条数 |

返回参数：

| 参数               | 类型   | 说明                    |
| ------------------ | ------ | ----------------------- |
| id                 | int    | 权限id                  |
| permissionName     | String | 权限名称                |
| permissionDescribe | String | 权限描述                |
| permissionKey      | String | 权限路径                |
| permissionValue    | String | 权限对应的角色          |
| parentPermissionId | int    | 父菜单id                |
| parentSort         | int    | 权限排序                |
| permissionStates   | int    | 权限状态(1=正常,2=删除) |
| permissions        | Object | 子菜单                  |

| 参数  | 类型   | 说明                                 |
| ----- | ------ | ------------------------------------ |
| msg   | String | 返回信息                             |
| data  | Object | 返回数据                             |
| code  | int    | 状态码(0=成功，500=失败,1000=无权限) |
| count | int    | 返回数据条数                         |

#### 搜索权限（模糊查询）

接口地址：localhost:8088/hotel/select/searchPermission

请求参数：

| 参数               | 是否必填                       | 类型   | 说明     |
| ------------------ | ------------------------------ | ------ | -------- |
| permissionName     | 至少有一项必填，也可以两项都填 | String | 权限名称 |
| permissionDescribe | 至少有一项必填，也可以两项都填 | String | 权限描述 |

返回参数：

| 参数               | 类型   | 说明                    |
| ------------------ | ------ | ----------------------- |
| id                 | int    | 权限id                  |
| permissionName     | String | 权限名称                |
| permissionDescribe | String | 权限描述                |
| permissionKey      | String | 权限路径                |
| permissionValue    | String | 权限对应的角色          |
| parentPermissionId | int    | 父菜单id                |
| parentSort         | int    | 权限排序                |
| permissionStates   | int    | 权限状态(1=正常,2=删除) |
| permissions        | Object | 子菜单                  |

| 参数  | 类型   | 说明                                 |
| ----- | ------ | ------------------------------------ |
| msg   | String | 返回信息                             |
| data  | Object | 返回数据                             |
| code  | int    | 状态码(0=成功，500=失败,1000=无权限) |
| count | int    | 返回数据条数                         |

#### 根据角色id查询资源权限

接口地址：localhost:8088/hotel/permission/getPermissionListByRoleId

请求参数：

| 参数   | 是否必填 | 类型 | 说明   |
| ------ | -------- | ---- | ------ |
| roleId | 是       | int  | 角色id |

返回参数：

| 参数  | 类型   | 说明                                 |
| ----- | ------ | ------------------------------------ |
| msg   | String | 返回信息                             |
| data  | Object | 返回数据                             |
| code  | int    | 状态码(0=成功，500=失败,1000=无权限) |
| count | int    | 返回数据条数                         |

#### 批量回收权限

接口地址：localhost:8088/hotel/permission/recyclePermissionBatch

请求参数：

| 参数 | 是否必填 | 类型 | 说明   |
| ---- | -------- | ---- | ------ |
| ids  | 是       | List | id集合 |

返回参数：

| 参数  | 类型   | 说明                                 |
| ----- | ------ | ------------------------------------ |
| msg   | String | 返回信息                             |
| data  | Object | 返回数据                             |
| code  | int    | 状态码(0=成功，500=失败,1000=无权限) |
| count | int    | 返回数据条数                         |

#### 将回收的权限批量恢复到正常状态

接口地址：localhost:8088/hotel/permission/recoveryRecyclePermissionBatch

请求参数：

| 参数 | 是否必填 | 类型 | 说明   |
| ---- | -------- | ---- | ------ |
| ids  | 是       | List | id集合 |

返回参数：

| 参数  | 类型   | 说明                                 |
| ----- | ------ | ------------------------------------ |
| msg   | String | 返回信息                             |
| data  | Object | 返回数据                             |
| code  | int    | 状态码(0=成功，500=失败,1000=无权限) |
| count | int    | 返回数据条数                         |

#### 批量删除资源权限

接口地址：localhost:8088/hotel/permission/deletePermissionBatch

请求参数：

| 参数 | 是否必填 | 类型 | 说明   |
| ---- | -------- | ---- | ------ |
| ids  | 是       | List | id集合 |

返回参数：

| 参数  | 类型   | 说明                                 |
| ----- | ------ | ------------------------------------ |
| msg   | String | 返回信息                             |
| data  | Object | 返回数据                             |
| code  | int    | 状态码(0=成功，500=失败,1000=无权限) |
| count | int    | 返回数据条数                         |

## 4.用户角色接口

#### 添加用户角色

接口地址：localhost:8088/hotel/userRole/insertUserRole

请求参数：

| 参数     | 是否必填 | 类型   | 说明   |
| -------- | -------- | ------ | ------ |
| username | 是       | String | 用户名 |
| roleId   | 是       | int    | 角色id |

返回参数：

| 参数  | 类型   | 说明                                 |
| ----- | ------ | ------------------------------------ |
| msg   | String | 返回信息                             |
| data  | Object | 返回数据                             |
| code  | int    | 状态码(0=成功，500=失败,1000=无权限) |
| count | int    | 返回数据条数                         |

#### 修改用户角色

接口地址：localhost:8088/hotel/userRole/updateUserRole

请求参数：

| 参数     | 是否必填 | 类型   | 说明       |
| -------- | -------- | ------ | ---------- |
| id       | 是       | int    | 用户角色id |
| username | 是       | String | 用户名     |
| roleId   | 是       | int    | 角色id     |

返回参数：

| 参数  | 类型   | 说明                                 |
| ----- | ------ | ------------------------------------ |
| msg   | String | 返回信息                             |
| data  | Object | 返回数据                             |
| code  | int    | 状态码(0=成功，500=失败,1000=无权限) |
| count | int    | 返回数据条数                         |

#### 删除用户角色

接口地址：localhost:8088/hotel/userRole/deleteUserRole

请求参数：

| 参数 | 是否必填 | 类型 | 说明       |
| ---- | -------- | ---- | ---------- |
| id   | 是       | int  | 用户角色id |

返回参数：

| 参数  | 类型   | 说明                                 |
| ----- | ------ | ------------------------------------ |
| msg   | String | 返回信息                             |
| data  | Object | 返回数据                             |
| code  | int    | 状态码(0=成功，500=失败,1000=无权限) |
| count | int    | 返回数据条数                         |

#### 根据id查询用户角色

接口地址：localhost:8088/hotel/select/getUserRoleById

请求参数：

| 参数 | 是否必填 | 类型 | 说明       |
| ---- | -------- | ---- | ---------- |
| id   | 是       | int  | 用户角色id |

返回参数：

| 参数            | 类型   | 说明       |
| --------------- | ------ | ---------- |
| id              | int    | 用户角色id |
| userId          | int    | 用户id     |
| roleId          | int    | 角色id     |
| userName        | String | 用户名     |
| roleName        | String | 角色名     |
| roleDescription | String | 角色描述   |

| 参数  | 类型   | 说明                                 |
| ----- | ------ | ------------------------------------ |
| msg   | String | 返回信息                             |
| data  | Object | 返回数据                             |
| code  | int    | 状态码(0=成功，500=失败,1000=无权限) |
| count | int    | 返回数据条数                         |

#### 获取用户角色列表

接口地址：localhost:8088/hotel/select/getUserRoleList

请求参数：无

返回参数：

| 参数            | 类型   | 说明       |
| --------------- | ------ | ---------- |
| id              | int    | 用户角色id |
| userId          | int    | 用户id     |
| roleId          | int    | 角色id     |
| userName        | String | 用户名     |
| roleName        | String | 角色名     |
| roleDescription | String | 角色描述   |

| 参数  | 类型   | 说明                                 |
| ----- | ------ | ------------------------------------ |
| msg   | String | 返回信息                             |
| data  | Object | 返回数据                             |
| code  | int    | 状态码(0=成功，500=失败,1000=无权限) |
| count | int    | 返回数据条数                         |

#### 批量删除用户角色

接口地址：localhost:8088/hotel/userRole/deleteUserRoleBatch

请求参数：

| 参数 | 是否必填 | 类型 | 说明   |
| ---- | -------- | ---- | ------ |
| ids  | 是       | List | id集合 |

返回参数：

| 参数  | 类型   | 说明                                 |
| ----- | ------ | ------------------------------------ |
| msg   | String | 返回信息                             |
| data  | Object | 返回数据                             |
| code  | int    | 状态码(0=成功，500=失败,1000=无权限) |
| count | int    | 返回数据条数                         |

## 5.角色权限接口

#### 添加角色权限

接口地址：localhost:8088/hotel/rolePermission/insertRolePermission

请求参数：

| 参数         | 是否必填 | 类型 | 说明   |
| ------------ | -------- | ---- | ------ |
| roleId       | 是       | int  | 角色id |
| permissionId | 是       | int  | 权限id |

返回参数：

| 参数  | 类型   | 说明                                 |
| ----- | ------ | ------------------------------------ |
| msg   | String | 返回信息                             |
| data  | Object | 返回数据                             |
| code  | int    | 状态码(0=成功，500=失败,1000=无权限) |
| count | int    | 返回数据条数                         |

#### 修改角色权限

接口地址：localhost:8088/hotel/rolePermission/updateRolePermission

请求参数：

| 参数         | 是否必填 | 类型 | 说明       |
| ------------ | -------- | ---- | ---------- |
| id           | 是       | int  | 角色权限id |
| roleId       | 是       | int  | 角色id     |
| permissionId | 是       | int  | 权限id     |

返回参数：

| 参数  | 类型   | 说明                                 |
| ----- | ------ | ------------------------------------ |
| msg   | String | 返回信息                             |
| data  | Object | 返回数据                             |
| code  | int    | 状态码(0=成功，500=失败,1000=无权限) |
| count | int    | 返回数据条数                         |

#### 删除角色权限

接口地址：localhost:8088/hotel/rolePermission/deleteRolePermission

请求参数：

| 参数 | 是否必填 | 类型 | 说明       |
| ---- | -------- | ---- | ---------- |
| id   | 是       | int  | 角色权限id |

返回参数：

| 参数  | 类型   | 说明                                 |
| ----- | ------ | ------------------------------------ |
| msg   | String | 返回信息                             |
| data  | Object | 返回数据                             |
| code  | int    | 状态码(0=成功，500=失败,1000=无权限) |
| count | int    | 返回数据条数                         |

#### 根据id查询角色权限

接口地址：localhost:8088/hotel/select/getRolePermissionById

请求参数：

| 参数 | 是否必填 | 类型 | 说明       |
| ---- | -------- | ---- | ---------- |
| id   | 是       | int  | 角色权限id |

返回参数：

| 参数               | 类型   | 说明       |
| ------------------ | ------ | ---------- |
| id                 | int    | 角色权限id |
| roleId             | int    | 角色id     |
| permissionId       | int    | 权限id     |
| roleName           | String | 角色名称   |
| roleDescribe       | String | 角色描述   |
| permissionName     | String | 权限名称   |
| permissionDescribe | String | 权限描述   |

| 参数  | 类型   | 说明                                 |
| ----- | ------ | ------------------------------------ |
| msg   | String | 返回信息                             |
| data  | Object | 返回数据                             |
| code  | int    | 状态码(0=成功，500=失败,1000=无权限) |
| count | int    | 返回数据条数                         |

#### 获取角色权限列表

接口地址：localhost:8088/hotel/select/getRolePermissionList

请求参数：无

返回参数：

| 参数               | 类型   | 说明       |
| ------------------ | ------ | ---------- |
| id                 | int    | 角色权限id |
| roleId             | int    | 角色id     |
| permissionId       | int    | 权限id     |
| roleName           | String | 角色名称   |
| roleDescribe       | String | 角色描述   |
| permissionName     | String | 权限名称   |
| permissionDescribe | String | 权限描述   |

| 参数  | 类型   | 说明                                 |
| ----- | ------ | ------------------------------------ |
| msg   | String | 返回信息                             |
| data  | Object | 返回数据                             |
| code  | int    | 状态码(0=成功，500=失败,1000=无权限) |
| count | int    | 返回数据条数                         |

#### 批量删除角色权限

接口地址：localhost:8088/hotel/rolePermission/deleteRolePermissionBatch

请求参数：

| 参数 | 是否必填 | 类型 | 说明   |
| ---- | -------- | ---- | ------ |
| ids  | 是       | List | id集合 |

返回参数：

| 参数  | 类型   | 说明                                 |
| ----- | ------ | ------------------------------------ |
| msg   | String | 返回信息                             |
| data  | Object | 返回数据                             |
| code  | int    | 状态码(0=成功，500=失败,1000=无权限) |
| count | int    | 返回数据条数                         |

## 6.客房接口

#### 添加客房

接口地址：localhost:8088/hotel/room/insertRoom

请求参数：

| 参数        | 是否必填 | 类型          | 说明     |
| ----------- | -------- | ------------- | -------- |
| roomFloor   | 是       | String        | 房间楼层 |
| roomNumber  | 是       | int           | 房间号   |
| roomPicture | 是       | MultipartFile | 房间图片 |
| roomIntro   | 否       | String        | 房间简介 |
| roomType    | 是       | String        | 房间类型 |
| roomMax     | 否       | String        | 最大人数 |
| roomArea    | 否       | String        | 占地面积 |
| roomBedType | 否       | String        | 床型     |
| roomPrice   | 是       | String        | 房间单价 |

返回参数：

| 参数  | 类型   | 说明                                 |
| ----- | ------ | ------------------------------------ |
| msg   | String | 返回信息                             |
| data  | Object | 返回数据                             |
| code  | int    | 状态码(0=成功，500=失败,1000=无权限) |
| count | int    | 返回数据条数                         |

#### 修改客房

接口地址：localhost:8088/hotel/room/updateRoom

请求参数：

| 参数        | 是否必填 | 类型          | 说明     |
| ----------- | -------- | ------------- | -------- |
| id          | 是       | int           | 房间id   |
| roomFloor   | 是       | String        | 房间楼层 |
| roomNumber  | 是       | int           | 房间号   |
| roomPicture | 是       | MultipartFile | 房间图片 |
| roomIntro   | 否       | String        | 房间简介 |
| roomType    | 是       | String        | 房间类型 |
| roomMax     | 否       | String        | 最大人数 |
| roomArea    | 否       | String        | 占地面积 |
| roomBedType | 否       | String        | 床型     |
| roomPrice   | 是       | String        | 房间单价 |

返回参数：

| 参数  | 类型   | 说明                                 |
| ----- | ------ | ------------------------------------ |
| msg   | String | 返回信息                             |
| data  | Object | 返回数据                             |
| code  | int    | 状态码(0=成功，500=失败,1000=无权限) |
| count | int    | 返回数据条数                         |

#### 根据id回收客房

接口地址：localhost:8088/hotel/room/recycleRoom

请求参数：

| 参数 | 是否必填 | 类型 | 说明   |
| ---- | -------- | ---- | ------ |
| id   | 是       | int  | 房间id |

返回参数：

| 参数  | 类型   | 说明                                 |
| ----- | ------ | ------------------------------------ |
| msg   | String | 返回信息                             |
| data  | Object | 返回数据                             |
| code  | int    | 状态码(0=成功，500=失败,1000=无权限) |
| count | int    | 返回数据条数                         |

#### 将回收的客房恢复到正常状态

接口地址：localhost:8088/hotel/room/recoveryRecycleRoom

请求参数：

| 参数 | 是否必填 | 类型 | 说明   |
| ---- | -------- | ---- | ------ |
| id   | 是       | int  | 房间id |

返回参数：

| 参数  | 类型   | 说明                                 |
| ----- | ------ | ------------------------------------ |
| msg   | String | 返回信息                             |
| data  | Object | 返回数据                             |
| code  | int    | 状态码(0=成功，500=失败,1000=无权限) |
| count | int    | 返回数据条数                         |

#### 根据id删除客房

接口地址：localhost:8088/hotel/room/deleteRoom

请求参数：

| 参数 | 是否必填 | 类型 | 说明   |
| ---- | -------- | ---- | ------ |
| id   | 是       | int  | 房间id |

返回参数：

| 参数  | 类型   | 说明                                 |
| ----- | ------ | ------------------------------------ |
| msg   | String | 返回信息                             |
| data  | Object | 返回数据                             |
| code  | int    | 状态码(0=成功，500=失败,1000=无权限) |
| count | int    | 返回数据条数                         |

#### 根据id查询客房

接口地址：localhost:8088/hotel/select/getRoomById

请求参数：

| 参数 | 是否必填 | 类型 | 说明   |
| ---- | -------- | ---- | ------ |
| id   | 是       | int  | 房间id |

返回参数：

| 参数            | 类型   | 说明                             |
| --------------- | ------ | -------------------------------- |
| id              | int    | 房间id                           |
| roomFloor       | String | 房间楼层                         |
| roomNumber      | int    | 房间号                           |
| roomPicture     | String | 房间封面图片                     |
| roomIntro       | String | 房间描述                         |
| roomType        | String | 房间类型                         |
| roomMax         | String | 最大人数                         |
| roomArea        | String | 占地面积                         |
| roomBedType     | String | 床型                             |
| roomPrice       | String | 房间单价                         |
| createrId       | int    | 创建者id                         |
| createrUsername | String | 创建者名称                       |
| createTime      | Date   | 创建时间                         |
| modifyId        | int    | 修改者id                         |
| modifyUsername  | String | 修改者名称                       |
| modifyTime      | Date   | 修改时间                         |
| roomStates      | int    | 房间状态(1=无人,2=有人,3=已预定) |
| existStates     | int    | 存在状态(1=正常,2=删除)          |

| 参数  | 类型   | 说明                                 |
| ----- | ------ | ------------------------------------ |
| msg   | String | 返回信息                             |
| data  | Object | 返回数据                             |
| code  | int    | 状态码(0=成功，500=失败,1000=无权限) |
| count | int    | 返回数据条数                         |

#### 获取正常状态的客房分页列表

接口地址：localhost:8088/hotel/select/getRoomList

请求参数：

| 参数  | 是否必填 | 类型 | 说明     |
| ----- | -------- | ---- | -------- |
| page  | 是       | int  | 当前页数 |
| limit | 是       | int  | 每页条数 |

返回参数：

| 参数            | 类型   | 说明                             |
| --------------- | ------ | -------------------------------- |
| id              | int    | 房间id                           |
| roomFloor       | String | 房间楼层                         |
| roomNumber      | int    | 房间号                           |
| roomPicture     | String | 房间封面图片                     |
| roomIntro       | String | 房间描述                         |
| roomType        | String | 房间类型                         |
| roomMax         | String | 最大人数                         |
| roomArea        | String | 占地面积                         |
| roomBedType     | String | 床型                             |
| roomPrice       | String | 房间单价                         |
| createrId       | int    | 创建者id                         |
| createrUsername | String | 创建者名称                       |
| createTime      | Date   | 创建时间                         |
| modifyId        | int    | 修改者id                         |
| modifyUsername  | String | 修改者名称                       |
| modifyTime      | Date   | 修改时间                         |
| roomStates      | int    | 房间状态(1=无人,2=有人,3=已预定) |
| existStates     | int    | 存在状态(1=正常,2=删除)          |

| 参数  | 类型   | 说明                                 |
| ----- | ------ | ------------------------------------ |
| msg   | String | 返回信息                             |
| data  | Object | 返回数据                             |
| code  | int    | 状态码(0=成功，500=失败,1000=无权限) |
| count | int    | 返回数据条数                         |

#### 获取回收的客房分页列表

接口地址：localhost:8088/hotel/select/getRecycleRoomList

请求参数：

| 参数  | 是否必填 | 类型 | 说明     |
| ----- | -------- | ---- | -------- |
| page  | 是       | int  | 当前页数 |
| limit | 是       | int  | 每页条数 |

返回参数：

| 参数            | 类型   | 说明                             |
| --------------- | ------ | -------------------------------- |
| id              | int    | 房间id                           |
| roomFloor       | String | 房间楼层                         |
| roomNumber      | int    | 房间号                           |
| roomPicture     | String | 房间封面图片                     |
| roomIntro       | String | 房间描述                         |
| roomType        | String | 房间类型                         |
| roomMax         | String | 最大人数                         |
| roomArea        | String | 占地面积                         |
| roomBedType     | String | 床型                             |
| roomPrice       | String | 房间单价                         |
| createrId       | int    | 创建者id                         |
| createrUsername | String | 创建者名称                       |
| createTime      | Date   | 创建时间                         |
| modifyId        | int    | 修改者id                         |
| modifyUsername  | String | 修改者名称                       |
| modifyTime      | Date   | 修改时间                         |
| roomStates      | int    | 房间状态(1=无人,2=有人,3=已预定) |
| existStates     | int    | 存在状态(1=正常,2=删除)          |

| 参数  | 类型   | 说明                                 |
| ----- | ------ | ------------------------------------ |
| msg   | String | 返回信息                             |
| data  | Object | 返回数据                             |
| code  | int    | 状态码(0=成功，500=失败,1000=无权限) |
| count | int    | 返回数据条数                         |

#### 搜索房间

接口地址：localhost:8088/hotel/select/searchRoom

请求参数：

| 参数       | 是否必填 | 类型 | 说明   |
| ---------- | -------- | ---- | ------ |
| roomNumber | 是       | int  | 房间号 |

返回参数：

| 参数            | 类型   | 说明                             |
| --------------- | ------ | -------------------------------- |
| id              | int    | 房间id                           |
| roomFloor       | String | 房间楼层                         |
| roomNumber      | int    | 房间号                           |
| roomPicture     | String | 房间封面图片                     |
| roomIntro       | String | 房间描述                         |
| roomType        | String | 房间类型                         |
| roomMax         | String | 最大人数                         |
| roomArea        | String | 占地面积                         |
| roomBedType     | String | 床型                             |
| roomPrice       | String | 房间单价                         |
| createrId       | int    | 创建者id                         |
| createrUsername | String | 创建者名称                       |
| createTime      | Date   | 创建时间                         |
| modifyId        | int    | 修改者id                         |
| modifyUsername  | String | 修改者名称                       |
| modifyTime      | Date   | 修改时间                         |
| roomStates      | int    | 房间状态(1=无人,2=有人,3=已预定) |
| existStates     | int    | 存在状态(1=正常,2=删除)          |

| 参数  | 类型   | 说明                                 |
| ----- | ------ | ------------------------------------ |
| msg   | String | 返回信息                             |
| data  | Object | 返回数据                             |
| code  | int    | 状态码(0=成功，500=失败,1000=无权限) |
| count | int    | 返回数据条数                         |

#### 根据楼层筛选

接口地址：localhost:8088/hotel/select/getRoomListByRoomFloor

请求参数：

| 参数      | 是否必填 | 类型   | 说明     |
| --------- | -------- | ------ | -------- |
| page      | 是       | int    | 当前页数 |
| limit     | 是       | int    | 每页条数 |
| roomFloor | 是       | String | 楼层     |

返回参数：

| 参数            | 类型   | 说明                             |
| --------------- | ------ | -------------------------------- |
| id              | int    | 房间id                           |
| roomFloor       | String | 房间楼层                         |
| roomNumber      | int    | 房间号                           |
| roomPicture     | String | 房间封面图片                     |
| roomIntro       | String | 房间描述                         |
| roomType        | String | 房间类型                         |
| roomMax         | String | 最大人数                         |
| roomArea        | String | 占地面积                         |
| roomBedType     | String | 床型                             |
| roomPrice       | String | 房间单价                         |
| createrId       | int    | 创建者id                         |
| createrUsername | String | 创建者名称                       |
| createTime      | Date   | 创建时间                         |
| modifyId        | int    | 修改者id                         |
| modifyUsername  | String | 修改者名称                       |
| modifyTime      | Date   | 修改时间                         |
| roomStates      | int    | 房间状态(1=无人,2=有人,3=已预定) |
| existStates     | int    | 存在状态(1=正常,2=删除)          |

| 参数  | 类型   | 说明                                 |
| ----- | ------ | ------------------------------------ |
| msg   | String | 返回信息                             |
| data  | Object | 返回数据                             |
| code  | int    | 状态码(0=成功，500=失败,1000=无权限) |
| count | int    | 返回数据条数                         |

#### 根据房间类型筛选

接口地址：localhost:8088/hotel/select/getRoomListByRoomType

请求参数：

| 参数     | 是否必填 | 类型   | 说明     |
| -------- | -------- | ------ | -------- |
| page     | 是       | int    | 当前页数 |
| limit    | 是       | int    | 每页条数 |
| roomType | 是       | String | 房间类型 |

返回参数：

| 参数            | 类型   | 说明                             |
| --------------- | ------ | -------------------------------- |
| id              | int    | 房间id                           |
| roomFloor       | String | 房间楼层                         |
| roomNumber      | int    | 房间号                           |
| roomPicture     | String | 房间封面图片                     |
| roomIntro       | String | 房间描述                         |
| roomType        | String | 房间类型                         |
| roomMax         | String | 最大人数                         |
| roomArea        | String | 占地面积                         |
| roomBedType     | String | 床型                             |
| roomPrice       | String | 房间单价                         |
| createrId       | int    | 创建者id                         |
| createrUsername | String | 创建者名称                       |
| createTime      | Date   | 创建时间                         |
| modifyId        | int    | 修改者id                         |
| modifyUsername  | String | 修改者名称                       |
| modifyTime      | Date   | 修改时间                         |
| roomStates      | int    | 房间状态(1=无人,2=有人,3=已预定) |
| existStates     | int    | 存在状态(1=正常,2=删除)          |

| 参数  | 类型   | 说明                                 |
| ----- | ------ | ------------------------------------ |
| msg   | String | 返回信息                             |
| data  | Object | 返回数据                             |
| code  | int    | 状态码(0=成功，500=失败,1000=无权限) |
| count | int    | 返回数据条数                         |

#### 根据床型筛选

接口地址：localhost:8088/hotel/select/getRoomListByRoomBedType

请求参数：

| 参数       | 是否必填 | 类型   | 说明     |
| ---------- | -------- | ------ | -------- |
| page       | 是       | int    | 当前页数 |
| limit      | 是       | int    | 每页条数 |
| roomNumber | 是       | String | 房间号   |

返回参数：

| 参数            | 类型   | 说明                             |
| --------------- | ------ | -------------------------------- |
| id              | int    | 房间id                           |
| roomFloor       | String | 房间楼层                         |
| roomNumber      | int    | 房间号                           |
| roomPicture     | String | 房间封面图片                     |
| roomIntro       | String | 房间描述                         |
| roomType        | String | 房间类型                         |
| roomMax         | String | 最大人数                         |
| roomArea        | String | 占地面积                         |
| roomBedType     | String | 床型                             |
| roomPrice       | String | 房间单价                         |
| createrId       | int    | 创建者id                         |
| createrUsername | String | 创建者名称                       |
| createTime      | Date   | 创建时间                         |
| modifyId        | int    | 修改者id                         |
| modifyUsername  | String | 修改者名称                       |
| modifyTime      | Date   | 修改时间                         |
| roomStates      | int    | 房间状态(1=无人,2=有人,3=已预定) |
| existStates     | int    | 存在状态(1=正常,2=删除)          |

| 参数  | 类型   | 说明                                 |
| ----- | ------ | ------------------------------------ |
| msg   | String | 返回信息                             |
| data  | Object | 返回数据                             |
| code  | int    | 状态码(0=成功，500=失败,1000=无权限) |
| count | int    | 返回数据条数                         |

#### 批量回收房间

接口地址：localhost:8088/hotel/room/recycleRoomBatch

请求参数：

| 参数 | 是否必填 | 类型 | 说明   |
| ---- | -------- | ---- | ------ |
| ids  | 是       | List | id集合 |

返回参数：

| 参数  | 类型   | 说明                                 |
| ----- | ------ | ------------------------------------ |
| msg   | String | 返回信息                             |
| data  | Object | 返回数据                             |
| code  | int    | 状态码(0=成功，500=失败,1000=无权限) |
| count | int    | 返回数据条数                         |

#### 将回收的房间批量恢复到正常状态

接口地址：localhost:8088/hotel/room/recoveryRecycleRoomBatch

请求参数：

| 参数 | 是否必填 | 类型 | 说明   |
| ---- | -------- | ---- | ------ |
| ids  | 是       | List | id集合 |

返回参数：

| 参数  | 类型   | 说明                                 |
| ----- | ------ | ------------------------------------ |
| msg   | String | 返回信息                             |
| data  | Object | 返回数据                             |
| code  | int    | 状态码(0=成功，500=失败,1000=无权限) |
| count | int    | 返回数据条数                         |

#### 批量删除房间

接口地址：localhost:8088/hotel/room/deleteRoomBatch

请求参数：

| 参数 | 是否必填 | 类型 | 说明   |
| ---- | -------- | ---- | ------ |
| ids  | 是       | List | id集合 |

返回参数：

| 参数  | 类型   | 说明                                 |
| ----- | ------ | ------------------------------------ |
| msg   | String | 返回信息                             |
| data  | Object | 返回数据                             |
| code  | int    | 状态码(0=成功，500=失败,1000=无权限) |
| count | int    | 返回数据条数                         |

## 7.商品接口

#### 添加商品

接口地址：localhost:8088/hotel/product/insertProduct

请求参数：

| 参数             | 是否必填 | 类型          | 说明     |
| ---------------- | -------- | ------------- | -------- |
| productName      | 是       | String        | 商品名称 |
| productPicture   | 是       | MultipartFile | 商品图片 |
| productIntro     | 否       | String        | 商品简介 |
| productType      | 是       | String        | 商品分类 |
| productUnitPrice | 是       | String        | 商品单价 |
| productNum       | 是       | int           | 商品数量 |

返回参数：

| 参数  | 类型   | 说明                                 |
| ----- | ------ | ------------------------------------ |
| msg   | String | 返回信息                             |
| data  | Object | 返回数据                             |
| code  | int    | 状态码(0=成功，500=失败,1000=无权限) |
| count | int    | 返回数据条数                         |

#### 修改商品

接口地址：localhost:8088/hotel/product/updateProduct

请求参数：

| 参数             | 是否必填 | 类型          | 说明     |
| ---------------- | -------- | ------------- | -------- |
| id               | 是       | int           | 商品id   |
| productName      | 是       | String        | 商品名称 |
| productPicture   | 是       | MultipartFile | 商品图片 |
| productIntro     | 否       | String        | 商品简介 |
| productType      | 是       | String        | 商品分类 |
| productUnitPrice | 是       | String        | 商品单价 |
| productNum       | 是       | int           | 商品数量 |

返回参数：

| 参数  | 类型   | 说明                                 |
| ----- | ------ | ------------------------------------ |
| msg   | String | 返回信息                             |
| data  | Object | 返回数据                             |
| code  | int    | 状态码(0=成功，500=失败,1000=无权限) |
| count | int    | 返回数据条数                         |

#### 根据id回收商品

接口地址：localhost:8088/hotel/product/recycleProduct

请求参数：

| 参数 | 是否必填 | 类型 | 说明   |
| ---- | -------- | ---- | ------ |
| id   | 是       | int  | 商品id |

返回参数：

| 参数  | 类型   | 说明                                 |
| ----- | ------ | ------------------------------------ |
| msg   | String | 返回信息                             |
| data  | Object | 返回数据                             |
| code  | int    | 状态码(0=成功，500=失败,1000=无权限) |
| count | int    | 返回数据条数                         |

#### 将回收的商品恢复到正常状态

接口地址：localhost:8088/hotel/product/recoveryRecycleProduct

请求参数：

| 参数 | 是否必填 | 类型 | 说明   |
| ---- | -------- | ---- | ------ |
| id   | 是       | int  | 商品id |

返回参数：

| 参数  | 类型   | 说明                                 |
| ----- | ------ | ------------------------------------ |
| msg   | String | 返回信息                             |
| data  | Object | 返回数据                             |
| code  | int    | 状态码(0=成功，500=失败,1000=无权限) |
| count | int    | 返回数据条数                         |

#### 根据id删除商品

接口地址：localhost:8088/hotel/product/getRecycleRoleList

请求参数：

| 参数 | 是否必填 | 类型 | 说明   |
| ---- | -------- | ---- | ------ |
| id   | 是       | int  | 商品id |

返回参数：

| 参数  | 类型   | 说明                                 |
| ----- | ------ | ------------------------------------ |
| msg   | String | 返回信息                             |
| data  | Object | 返回数据                             |
| code  | int    | 状态码(0=成功，500=失败,1000=无权限) |
| count | int    | 返回数据条数                         |

#### 根据id查询商品

接口地址：localhost:8088/hotel/select/getProductById

请求参数：

| 参数 | 是否必填 | 类型 | 说明   |
| ---- | -------- | ---- | ------ |
| id   | 是       | int  | 商品id |

返回参数：

| 参数              | 类型   | 说明                    |
| ----------------- | ------ | ----------------------- |
| id                | int    | 商品id                  |
| productName       | String | 商品名称                |
| productPicture    | String | 商品图片                |
| productIntro      | String | 商品简介                |
| productType       | String | 商品分类                |
| productUnitPrice  | String | 商品单价                |
| productNum        | int    | 商品数量                |
| productPopularity | String | 商品热度                |
| createrId         | int    | 创建者id                |
| createrUsername   | String | 创建者名称              |
| createTime        | Date   | 创建时间                |
| modifierId        | int    | 修改者id                |
| modifierUsername  | String | 修改者名称              |
| modifyTime        | Date   | 修改时间                |
| productInstock    | int    | 是否有货(1=有货,2=无货) |
| productStates     | int    | 存在状态(1=正常,2=删除) |

| 参数  | 类型   | 说明                                 |
| ----- | ------ | ------------------------------------ |
| msg   | String | 返回信息                             |
| data  | Object | 返回数据                             |
| code  | int    | 状态码(0=成功，500=失败,1000=无权限) |
| count | int    | 返回数据条数                         |

#### 获取正常状态的商品分页列表

接口地址：localhost:8088/hotel/select/getProductList

请求参数：

| 参数  | 是否必填 | 类型 | 说明     |
| ----- | -------- | ---- | -------- |
| page  | 是       | int  | 当前页数 |
| limit | 是       | int  | 每页条数 |

返回参数：

| 参数              | 类型   | 说明                    |
| ----------------- | ------ | ----------------------- |
| id                | int    | 商品id                  |
| productName       | String | 商品名称                |
| productPicture    | String | 商品图片                |
| productIntro      | String | 商品简介                |
| productType       | String | 商品分类                |
| productUnitPrice  | String | 商品单价                |
| productNum        | int    | 商品数量                |
| productPopularity | String | 商品热度                |
| createrId         | int    | 创建者id                |
| createrUsername   | String | 创建者名称              |
| createTime        | Date   | 创建时间                |
| modifierId        | int    | 修改者id                |
| modifierUsername  | String | 修改者名称              |
| modifyTime        | Date   | 修改时间                |
| productInstock    | int    | 是否有货(1=有货,2=无货) |
| productStates     | int    | 存在状态(1=正常,2=删除) |

| 参数  | 类型   | 说明                                 |
| ----- | ------ | ------------------------------------ |
| msg   | String | 返回信息                             |
| data  | Object | 返回数据                             |
| code  | int    | 状态码(0=成功，500=失败,1000=无权限) |
| count | int    | 返回数据条数                         |

#### 获取回收的商品分页列表

接口地址：localhost:8088/hotel/select/getRecycleProductList

请求参数：

| 参数  | 是否必填 | 类型 | 说明     |
| ----- | -------- | ---- | -------- |
| page  | 是       | int  | 当前页数 |
| limit | 是       | int  | 每页条数 |

返回参数：

| 参数              | 类型   | 说明                    |
| ----------------- | ------ | ----------------------- |
| id                | int    | 商品id                  |
| productName       | String | 商品名称                |
| productPicture    | String | 商品图片                |
| productIntro      | String | 商品简介                |
| productType       | String | 商品分类                |
| productUnitPrice  | String | 商品单价                |
| productNum        | int    | 商品数量                |
| productPopularity | String | 商品热度                |
| createrId         | int    | 创建者id                |
| createrUsername   | String | 创建者名称              |
| createTime        | Date   | 创建时间                |
| modifierId        | int    | 修改者id                |
| modifierUsername  | String | 修改者名称              |
| modifyTime        | Date   | 修改时间                |
| productInstock    | int    | 是否有货(1=有货,2=无货) |
| productStates     | int    | 存在状态(1=正常,2=删除) |

| 参数  | 类型   | 说明                                 |
| ----- | ------ | ------------------------------------ |
| msg   | String | 返回信息                             |
| data  | Object | 返回数据                             |
| code  | int    | 状态码(0=成功，500=失败,1000=无权限) |
| count | int    | 返回数据条数                         |

#### 搜索商品（模糊查询）

接口地址：localhost:8088/hotel/select/searchProduct

请求参数：

| 参数        | 是否必填 | 类型   | 说明     |
| ----------- | -------- | ------ | -------- |
| productName | 是       | String | 商品名称 |

返回参数：

| 参数              | 类型   | 说明                    |
| ----------------- | ------ | ----------------------- |
| id                | int    | 商品id                  |
| productName       | String | 商品名称                |
| productPicture    | String | 商品图片                |
| productIntro      | String | 商品简介                |
| productType       | String | 商品分类                |
| productUnitPrice  | String | 商品单价                |
| productNum        | int    | 商品数量                |
| productPopularity | String | 商品热度                |
| createrId         | int    | 创建者id                |
| createrUsername   | String | 创建者名称              |
| createTime        | Date   | 创建时间                |
| modifierId        | int    | 修改者id                |
| modifierUsername  | String | 修改者名称              |
| modifyTime        | Date   | 修改时间                |
| productInstock    | int    | 是否有货(1=有货,2=无货) |
| productStates     | int    | 存在状态(1=正常,2=删除) |

| 参数  | 类型   | 说明                                 |
| ----- | ------ | ------------------------------------ |
| msg   | String | 返回信息                             |
| data  | Object | 返回数据                             |
| code  | int    | 状态码(0=成功，500=失败,1000=无权限) |
| count | int    | 返回数据条数                         |

#### 根据商品热度降序排序

接口地址：localhost:8088/hotel/select/getProductListByProductPopularitySortDesc

请求参数：

| 参数  | 是否必填 | 类型 | 说明     |
| ----- | -------- | ---- | -------- |
| page  | 是       | int  | 当前页数 |
| limit | 是       | int  | 每页条数 |

返回参数：

| 参数              | 类型   | 说明                    |
| ----------------- | ------ | ----------------------- |
| id                | int    | 商品id                  |
| productName       | String | 商品名称                |
| productPicture    | String | 商品图片                |
| productIntro      | String | 商品简介                |
| productType       | String | 商品分类                |
| productUnitPrice  | String | 商品单价                |
| productNum        | int    | 商品数量                |
| productPopularity | String | 商品热度                |
| createrId         | int    | 创建者id                |
| createrUsername   | String | 创建者名称              |
| createTime        | Date   | 创建时间                |
| modifierId        | int    | 修改者id                |
| modifierUsername  | String | 修改者名称              |
| modifyTime        | Date   | 修改时间                |
| productInstock    | int    | 是否有货(1=有货,2=无货) |
| productStates     | int    | 存在状态(1=正常,2=删除) |

| 参数  | 类型   | 说明                                 |
| ----- | ------ | ------------------------------------ |
| msg   | String | 返回信息                             |
| data  | Object | 返回数据                             |
| code  | int    | 状态码(0=成功，500=失败,1000=无权限) |
| count | int    | 返回数据条数                         |

#### 根据商品分类筛选

接口地址：localhost:8088/hotel/select/getProductListByProductType

请求参数：

| 参数        | 是否必填 | 类型   | 说明     |
| ----------- | -------- | ------ | -------- |
| page        | 是       | int    | 当前页数 |
| limit       | 是       | int    | 每页条数 |
| productType | 是       | String | 商品分类 |

返回参数：

| 参数              | 类型   | 说明                    |
| ----------------- | ------ | ----------------------- |
| id                | int    | 商品id                  |
| productName       | String | 商品名称                |
| productPicture    | String | 商品图片                |
| productIntro      | String | 商品简介                |
| productType       | String | 商品分类                |
| productUnitPrice  | String | 商品单价                |
| productNum        | int    | 商品数量                |
| productPopularity | String | 商品热度                |
| createrId         | int    | 创建者id                |
| createrUsername   | String | 创建者名称              |
| createTime        | Date   | 创建时间                |
| modifierId        | int    | 修改者id                |
| modifierUsername  | String | 修改者名称              |
| modifyTime        | Date   | 修改时间                |
| productInstock    | int    | 是否有货(1=有货,2=无货) |
| productStates     | int    | 存在状态(1=正常,2=删除) |

| 参数  | 类型   | 说明                                 |
| ----- | ------ | ------------------------------------ |
| msg   | String | 返回信息                             |
| data  | Object | 返回数据                             |
| code  | int    | 状态码(0=成功，500=失败,1000=无权限) |
| count | int    | 返回数据条数                         |

#### 批量回收商品

接口地址：localhost:8088/hotel/product/recycleProductBatch

请求参数：

| 参数 | 是否必填 | 类型 | 说明   |
| ---- | -------- | ---- | ------ |
| ids  | 是       | List | id集合 |

返回参数：

| 参数  | 类型   | 说明                                 |
| ----- | ------ | ------------------------------------ |
| msg   | String | 返回信息                             |
| data  | Object | 返回数据                             |
| code  | int    | 状态码(0=成功，500=失败,1000=无权限) |
| count | int    | 返回数据条数                         |

#### 将回收的商品批量恢复到正常状态

接口地址：localhost:8088/hotel/product/recoveryRecycleProductBatch

请求参数：

| 参数 | 是否必填 | 类型 | 说明   |
| ---- | -------- | ---- | ------ |
| ids  | 是       | List | id集合 |

返回参数：

| 参数  | 类型   | 说明                                 |
| ----- | ------ | ------------------------------------ |
| msg   | String | 返回信息                             |
| data  | Object | 返回数据                             |
| code  | int    | 状态码(0=成功，500=失败,1000=无权限) |
| count | int    | 返回数据条数                         |

#### 批量删除商品

接口地址：localhost:8088/hotel/product/deleteProductBatch

请求参数：

| 参数 | 是否必填 | 类型 | 说明   |
| ---- | -------- | ---- | ------ |
| ids  | 是       | List | id集合 |

返回参数：

| 参数  | 类型   | 说明                                 |
| ----- | ------ | ------------------------------------ |
| msg   | String | 返回信息                             |
| data  | Object | 返回数据                             |
| code  | int    | 状态码(0=成功，500=失败,1000=无权限) |
| count | int    | 返回数据条数                         |

## 8.餐厅接口

#### 添加菜单

接口地址：localhost:8088/hotel/restaurant/insertRestaurant

请求参数：

| 参数              | 是否必填 | 类型          | 说明                    |
| ----------------- | -------- | ------------- | ----------------------- |
| restaurantType    | 是       | String        | 菜品分类                |
| restaurantPicture | 是       | MultipartFile | 菜品图片                |
| restaurantIntro   | 否       | String        | 菜品名                  |
| foodUnitPrice     | 是       | String        | 菜品单价                |
| foodInstock       | 是       | int           | 是否有货(1=有货,2=无货) |

返回参数：

| 参数  | 类型   | 说明                                 |
| ----- | ------ | ------------------------------------ |
| msg   | String | 返回信息                             |
| data  | Object | 返回数据                             |
| code  | int    | 状态码(0=成功，500=失败,1000=无权限) |
| count | int    | 返回数据条数                         |

#### 修改菜单

接口地址：localhost:8088/hotel/restaurant/updateRestaurant

请求参数：

| 参数              | 是否必填 | 类型          | 说明                    |
| ----------------- | -------- | ------------- | ----------------------- |
| id                | 是       | int           | 菜品id                  |
| restaurantType    | 是       | String        | 菜品分类                |
| restaurantPicture | 是       | MultipartFile | 菜品图片                |
| restaurantIntro   | 否       | String        | 菜品名                  |
| foodUnitPrice     | 是       | String        | 菜品单价                |
| foodInstock       | 是       | int           | 是否有货(1=有货,2=无货) |

返回参数：

| 参数  | 类型   | 说明                                 |
| ----- | ------ | ------------------------------------ |
| msg   | String | 返回信息                             |
| data  | Object | 返回数据                             |
| code  | int    | 状态码(0=成功，500=失败,1000=无权限) |
| count | int    | 返回数据条数                         |

#### 根据id回收菜单

接口地址：localhost:8088/hotel/restaurant/recycleRestaurant

请求参数：

| 参数 | 是否必填 | 类型 | 说明   |
| ---- | -------- | ---- | ------ |
| id   | 是       | int  | 菜品id |

返回参数：

| 参数  | 类型   | 说明                                 |
| ----- | ------ | ------------------------------------ |
| msg   | String | 返回信息                             |
| data  | Object | 返回数据                             |
| code  | int    | 状态码(0=成功，500=失败,1000=无权限) |
| count | int    | 返回数据条数                         |

#### 将回收的菜单恢复到正常状态

接口地址：localhost:8088/hotel/restaurant/recoveryRecycleRestaurant

请求参数：

| 参数 | 是否必填 | 类型 | 说明   |
| ---- | -------- | ---- | ------ |
| id   | 是       | int  | 菜品id |

返回参数：

| 参数  | 类型   | 说明                                 |
| ----- | ------ | ------------------------------------ |
| msg   | String | 返回信息                             |
| data  | Object | 返回数据                             |
| code  | int    | 状态码(0=成功，500=失败,1000=无权限) |
| count | int    | 返回数据条数                         |

#### 根据id删除菜单

接口地址：localhost:8088/hotel/restaurant/deleteRestaurant

请求参数：

| 参数 | 是否必填 | 类型 | 说明   |
| ---- | -------- | ---- | ------ |
| id   | 是       | int  | 菜品id |

返回参数：

| 参数  | 类型   | 说明                                 |
| ----- | ------ | ------------------------------------ |
| msg   | String | 返回信息                             |
| data  | Object | 返回数据                             |
| code  | int    | 状态码(0=成功，500=失败,1000=无权限) |
| count | int    | 返回数据条数                         |

#### 根据id查询菜单

接口地址：localhost:8088/hotel/select/getRestaurantById

请求参数：

| 参数 | 是否必填 | 类型 | 说明   |
| ---- | -------- | ---- | ------ |
| id   | 是       | int  | 菜品id |

返回参数：

| 参数              | 类型   | 说明                    |
| ----------------- | ------ | ----------------------- |
| id                | int    | 菜品id                  |
| restaurantType    | String | 菜品分类                |
| restaurantPicture | String | 菜品图片                |
| restaurantIntro   | String | 菜品名                  |
| foodUnitPrice     | String | 菜品单价                |
| foodPopularity    | String | 菜品热度                |
| registerDate      | Date   | 上架时间                |
| createrId         | int    | 创建者id                |
| createrUsername   | String | 创建者名称              |
| createTime        | Date   | 创建时间                |
| modifierId        | int    | 修改者id                |
| modifierUsername  | String | 修改者名称              |
| modifyTime        | Date   | 修改时间                |
| foodInstock       | int    | 是否有货(1=有货,2=无货) |
| foodStates        | int    | 存在状态(1=正常,2=删除) |

| 参数  | 类型   | 说明                                 |
| ----- | ------ | ------------------------------------ |
| msg   | String | 返回信息                             |
| data  | Object | 返回数据                             |
| code  | int    | 状态码(0=成功，500=失败,1000=无权限) |
| count | int    | 返回数据条数                         |

#### 获取正常状态的菜单分页列表

接口地址：localhost:8088/hotel/select/getRestaurantList

请求参数：

| 参数  | 是否必填 | 类型 | 说明     |
| ----- | -------- | ---- | -------- |
| page  | 是       | int  | 当前页数 |
| limit | 是       | int  | 每页条数 |

返回参数：

| 参数              | 类型   | 说明                    |
| ----------------- | ------ | ----------------------- |
| id                | int    | 菜品id                  |
| restaurantType    | String | 菜品分类                |
| restaurantPicture | String | 菜品图片                |
| restaurantIntro   | String | 菜品名                  |
| foodUnitPrice     | String | 菜品单价                |
| foodPopularity    | String | 菜品热度                |
| registerDate      | Date   | 上架时间                |
| createrId         | int    | 创建者id                |
| createrUsername   | String | 创建者名称              |
| createTime        | Date   | 创建时间                |
| modifierId        | int    | 修改者id                |
| modifierUsername  | String | 修改者名称              |
| modifyTime        | Date   | 修改时间                |
| foodInstock       | int    | 是否有货(1=有货,2=无货) |
| foodStates        | int    | 存在状态(1=正常,2=删除) |

| 参数  | 类型   | 说明                                 |
| ----- | ------ | ------------------------------------ |
| msg   | String | 返回信息                             |
| data  | Object | 返回数据                             |
| code  | int    | 状态码(0=成功，500=失败,1000=无权限) |
| count | int    | 返回数据条数                         |

#### 获取回收的菜单分页列表

接口地址：localhost:8088/hotel/select/getRecycleRestaurantList

请求参数：

| 参数  | 是否必填 | 类型 | 说明     |
| ----- | -------- | ---- | -------- |
| page  | 是       | int  | 当前页数 |
| limit | 是       | int  | 每页条数 |

返回参数：

| 参数              | 类型   | 说明                    |
| ----------------- | ------ | ----------------------- |
| id                | int    | 菜品id                  |
| restaurantType    | String | 菜品分类                |
| restaurantPicture | String | 菜品图片                |
| restaurantIntro   | String | 菜品名                  |
| foodUnitPrice     | String | 菜品单价                |
| foodPopularity    | String | 菜品热度                |
| registerDate      | Date   | 上架时间                |
| createrId         | int    | 创建者id                |
| createrUsername   | String | 创建者名称              |
| createTime        | Date   | 创建时间                |
| modifierId        | int    | 修改者id                |
| modifierUsername  | String | 修改者名称              |
| modifyTime        | Date   | 修改时间                |
| foodInstock       | int    | 是否有货(1=有货,2=无货) |
| foodStates        | int    | 存在状态(1=正常,2=删除) |

| 参数  | 类型   | 说明                                 |
| ----- | ------ | ------------------------------------ |
| msg   | String | 返回信息                             |
| data  | Object | 返回数据                             |
| code  | int    | 状态码(0=成功，500=失败,1000=无权限) |
| count | int    | 返回数据条数                         |

#### 搜索用户（模糊查询）

接口地址：localhost:8088/hotel/select/searchRestaurant

请求参数：

| 参数            | 是否必填 | 类型   | 说明   |
| --------------- | -------- | ------ | ------ |
| restaurantIntro | 是       | String | 菜品名 |

返回参数：

| 参数              | 类型   | 说明                    |
| ----------------- | ------ | ----------------------- |
| id                | int    | 菜品id                  |
| restaurantType    | String | 菜品分类                |
| restaurantPicture | String | 菜品图片                |
| restaurantIntro   | String | 菜品名                  |
| foodUnitPrice     | String | 菜品单价                |
| foodPopularity    | String | 菜品热度                |
| registerDate      | Date   | 上架时间                |
| createrId         | int    | 创建者id                |
| createrUsername   | String | 创建者名称              |
| createTime        | Date   | 创建时间                |
| modifierId        | int    | 修改者id                |
| modifierUsername  | String | 修改者名称              |
| modifyTime        | Date   | 修改时间                |
| foodInstock       | int    | 是否有货(1=有货,2=无货) |
| foodStates        | int    | 存在状态(1=正常,2=删除) |

| 参数  | 类型   | 说明                                 |
| ----- | ------ | ------------------------------------ |
| msg   | String | 返回信息                             |
| data  | Object | 返回数据                             |
| code  | int    | 状态码(0=成功，500=失败,1000=无权限) |
| count | int    | 返回数据条数                         |

#### 根据商品热度降序排序

接口地址：localhost:8088/hotel/select/getRestaurantListByFoodPopularitySortDesc

请求参数：

| 参数  | 是否必填 | 类型 | 说明     |
| ----- | -------- | ---- | -------- |
| page  | 是       | int  | 当前页数 |
| limit | 是       | int  | 每页条数 |

返回参数：

| 参数              | 类型   | 说明                    |
| ----------------- | ------ | ----------------------- |
| id                | int    | 菜品id                  |
| restaurantType    | String | 菜品分类                |
| restaurantPicture | String | 菜品图片                |
| restaurantIntro   | String | 菜品名                  |
| foodUnitPrice     | String | 菜品单价                |
| foodPopularity    | String | 菜品热度                |
| registerDate      | Date   | 上架时间                |
| createrId         | int    | 创建者id                |
| createrUsername   | String | 创建者名称              |
| createTime        | Date   | 创建时间                |
| modifierId        | int    | 修改者id                |
| modifierUsername  | String | 修改者名称              |
| modifyTime        | Date   | 修改时间                |
| foodInstock       | int    | 是否有货(1=有货,2=无货) |
| foodStates        | int    | 存在状态(1=正常,2=删除) |

| 参数  | 类型   | 说明                                 |
| ----- | ------ | ------------------------------------ |
| msg   | String | 返回信息                             |
| data  | Object | 返回数据                             |
| code  | int    | 状态码(0=成功，500=失败,1000=无权限) |
| count | int    | 返回数据条数                         |

#### 根据菜单分类筛选

接口地址：localhost:8088/hotel/select/getRestaurantListByRestaurantType

请求参数：

| 参数           | 是否必填 | 类型   | 说明             |
| -------------- | -------- | ------ | ---------------- |
| page           | 是       | int    | 当前页数         |
| limit          | 是       | int    | 每页条数         |
| restaurantType | 是       | String | 菜品分类(早中晚) |

返回参数：

| 参数              | 类型   | 说明                    |
| ----------------- | ------ | ----------------------- |
| id                | int    | 菜品id                  |
| restaurantType    | String | 菜品分类                |
| restaurantPicture | String | 菜品图片                |
| restaurantIntro   | String | 菜品名                  |
| foodUnitPrice     | String | 菜品单价                |
| foodPopularity    | String | 菜品热度                |
| registerDate      | Date   | 上架时间                |
| createrId         | int    | 创建者id                |
| createrUsername   | String | 创建者名称              |
| createTime        | Date   | 创建时间                |
| modifierId        | int    | 修改者id                |
| modifierUsername  | String | 修改者名称              |
| modifyTime        | Date   | 修改时间                |
| foodInstock       | int    | 是否有货(1=有货,2=无货) |
| foodStates        | int    | 存在状态(1=正常,2=删除) |

| 参数  | 类型   | 说明                                 |
| ----- | ------ | ------------------------------------ |
| msg   | String | 返回信息                             |
| data  | Object | 返回数据                             |
| code  | int    | 状态码(0=成功，500=失败,1000=无权限) |
| count | int    | 返回数据条数                         |

#### 批量回收菜单

接口地址：localhost:8088/hotel/restaurant/recycleRestaurantBatch

请求参数：

| 参数 | 是否必填 | 类型 | 说明   |
| ---- | -------- | ---- | ------ |
| ids  | 是       | List | id集合 |

返回参数：

| 参数  | 类型   | 说明                                 |
| ----- | ------ | ------------------------------------ |
| msg   | String | 返回信息                             |
| data  | Object | 返回数据                             |
| code  | int    | 状态码(0=成功，500=失败,1000=无权限) |
| count | int    | 返回数据条数                         |

#### 将回收的菜单批量恢复到正常状态

接口地址：localhost:8088/hotel/restaurant/recoveryRecycleRestaurantBatch

请求参数：

| 参数 | 是否必填 | 类型 | 说明   |
| ---- | -------- | ---- | ------ |
| ids  | 是       | List | id集合 |

返回参数：

| 参数  | 类型   | 说明                                 |
| ----- | ------ | ------------------------------------ |
| msg   | String | 返回信息                             |
| data  | Object | 返回数据                             |
| code  | int    | 状态码(0=成功，500=失败,1000=无权限) |
| count | int    | 返回数据条数                         |

#### 批量删除菜单

接口地址：localhost:8088/hotel/restaurant/deleteRestaurantBatch

请求参数：

| 参数 | 是否必填 | 类型 | 说明   |
| ---- | -------- | ---- | ------ |
| ids  | 是       | List | id集合 |

返回参数：

| 参数  | 类型   | 说明                                 |
| ----- | ------ | ------------------------------------ |
| msg   | String | 返回信息                             |
| data  | Object | 返回数据                             |
| code  | int    | 状态码(0=成功，500=失败,1000=无权限) |
| count | int    | 返回数据条数                         |

## 9.用户商品订单接口

#### 用户下单

接口地址：localhost:8088/hotel/userProduct/insertUserProduct

请求参数：

| 参数       | 是否必填 | 类型 | 说明     |
| ---------- | -------- | ---- | -------- |
| userId     | 是       | int  | 用户id   |
| productId  | 是       | int  | 商品id   |
| productNum | 是       | int  | 商品数量 |

返回参数：

| 参数  | 类型   | 说明                                 |
| ----- | ------ | ------------------------------------ |
| msg   | String | 返回信息                             |
| data  | Object | 返回数据                             |
| code  | int    | 状态码(0=成功，500=失败,1000=无权限) |
| count | int    | 返回数据条数                         |

#### 修改订单

接口地址：localhost:8088/hotel/userProduct/updateUserProduct

请求参数：

| 参数             | 是否必填 | 类型 | 说明               |
| ---------------- | -------- | ---- | ------------------ |
| id               | 是       | int  | 用户商品id         |
| userId           | 是       | int  | 用户id             |
| productIdBefore  | 是       | int  | 修改之前的商品id   |
| productNumBefore | 是       | int  | 修改之前的商品数量 |
| productId        | 是       | int  | 修改之后的商品id   |
| productNum       | 是       | int  | 修改之后的商品数量 |

返回参数：

| 参数  | 类型   | 说明                                 |
| ----- | ------ | ------------------------------------ |
| msg   | String | 返回信息                             |
| data  | Object | 返回数据                             |
| code  | int    | 状态码(0=成功，500=失败,1000=无权限) |
| count | int    | 返回数据条数                         |

#### 交易完成（送达）

接口地址：localhost:8088/hotel/userProduct/cancel

请求参数：

| 参数 | 是否必填 | 类型 | 说明       |
| ---- | -------- | ---- | ---------- |
| id   | 是       | int  | 用户商品id |

返回参数：

| 参数  | 类型   | 说明                                 |
| ----- | ------ | ------------------------------------ |
| msg   | String | 返回信息                             |
| data  | Object | 返回数据                             |
| code  | int    | 状态码(0=成功，500=失败,1000=无权限) |
| count | int    | 返回数据条数                         |

#### 取消订单

接口地址：localhost:8088/hotel/userProduct/deleteUserProduct

请求参数：

| 参数       | 是否必填 | 类型 | 说明       |
| ---------- | -------- | ---- | ---------- |
| id         | 是       | int  | 用户商品id |
| productId  | 是       | int  | 商品id     |
| productNum | 是       | int  | 商品数量   |

返回参数：

| 参数  | 类型   | 说明                                 |
| ----- | ------ | ------------------------------------ |
| msg   | String | 返回信息                             |
| data  | Object | 返回数据                             |
| code  | int    | 状态码(0=成功，500=失败,1000=无权限) |
| count | int    | 返回数据条数                         |

#### 根据id查询订单

接口地址：localhost:8088/hotel/select/getUserProductById

请求参数：

| 参数 | 类型 | 说明       |
| ---- | ---- | ---------- |
| id   | int  | 用户商品id |

返回参数：

| 参数               | 类型   | 说明                        |
| ------------------ | ------ | --------------------------- |
| id                 | int    | 用户商品id                  |
| userId             | int    | 用户id                      |
| productId          | int    | 商品id                      |
| username           | String | 用户名                      |
| productName        | String | 商品名称                    |
| productPrice       | String | 商品单价                    |
| productNum         | int    | 商品数量                    |
| orderTime          | Date   | 下单时间                    |
| orderOverdueStates | int    | 订单是否过期(1=正常,2=过期) |
| orderStates        | int    | 支付状态(1=已支付,2=未支付) |

| 参数  | 类型   | 说明                                 |
| ----- | ------ | ------------------------------------ |
| msg   | String | 返回信息                             |
| data  | Object | 返回数据                             |
| code  | int    | 状态码(0=成功，500=失败,1000=无权限) |
| count | int    | 返回数据条数                         |

#### 获取用户商品订单分页列表

接口地址：localhost:8088/hotel/select/getUserProductList

请求参数：

| 参数  | 是否必填 | 类型 | 说明     |
| ----- | -------- | ---- | -------- |
| page  | 是       | int  | 当前页数 |
| limit | 是       | int  | 每页条数 |

返回参数：

| 参数               | 类型   | 说明                        |
| ------------------ | ------ | --------------------------- |
| id                 | int    | 用户商品id                  |
| userId             | int    | 用户id                      |
| productId          | int    | 商品id                      |
| username           | String | 用户名                      |
| productName        | String | 商品名称                    |
| productPrice       | String | 商品单价                    |
| productNum         | int    | 商品数量                    |
| orderTime          | Date   | 下单时间                    |
| orderOverdueStates | int    | 订单是否过期(1=正常,2=过期) |
| orderStates        | int    | 支付状态(1=已支付,2=未支付) |

| 参数  | 类型   | 说明                                 |
| ----- | ------ | ------------------------------------ |
| msg   | String | 返回信息                             |
| data  | Object | 返回数据                             |
| code  | int    | 状态码(0=成功，500=失败,1000=无权限) |
| count | int    | 返回数据条数                         |

#### 搜索订单（模糊查询）

接口地址：localhost:8088/hotel/select/searchUserProduct

请求参数：

| 参数        | 是否必填                       | 类型   | 说明     |
| ----------- | ------------------------------ | ------ | -------- |
| orderNumber | 至少有一项必填，也可以两项都填 | String | 订单编号 |
| username    | 至少有一项必填，也可以两项都填 | String | 用户名   |

返回参数：

| 参数               | 类型   | 说明                        |
| ------------------ | ------ | --------------------------- |
| id                 | int    | 用户商品id                  |
| userId             | int    | 用户id                      |
| productId          | int    | 商品id                      |
| username           | String | 用户名                      |
| productName        | String | 商品名称                    |
| productPrice       | String | 商品单价                    |
| productNum         | int    | 商品数量                    |
| orderTime          | Date   | 下单时间                    |
| orderOverdueStates | int    | 订单是否过期(1=正常,2=过期) |
| orderStates        | int    | 支付状态(1=已支付,2=未支付) |

| 参数  | 类型   | 说明                                 |
| ----- | ------ | ------------------------------------ |
| msg   | String | 返回信息                             |
| data  | Object | 返回数据                             |
| code  | int    | 状态码(0=成功，500=失败,1000=无权限) |
| count | int    | 返回数据条数                         |

#### 查询已付款的订单

接口地址：localhost:8088/hotel/select/getPaidUserProduct

请求参数：

| 参数  | 是否必填 | 类型 | 说明     |
| ----- | -------- | ---- | -------- |
| page  | 是       | int  | 当前页数 |
| limit | 是       | int  | 每页条数 |

返回参数：

| 参数               | 类型   | 说明                        |
| ------------------ | ------ | --------------------------- |
| id                 | int    | 用户商品id                  |
| userId             | int    | 用户id                      |
| productId          | int    | 商品id                      |
| username           | String | 用户名                      |
| productName        | String | 商品名称                    |
| productPrice       | String | 商品单价                    |
| productNum         | int    | 商品数量                    |
| orderTime          | Date   | 下单时间                    |
| orderOverdueStates | int    | 订单是否过期(1=正常,2=过期) |
| orderStates        | int    | 支付状态(1=已支付,2=未支付) |

| 参数  | 类型   | 说明                                 |
| ----- | ------ | ------------------------------------ |
| msg   | String | 返回信息                             |
| data  | Object | 返回数据                             |
| code  | int    | 状态码(0=成功，500=失败,1000=无权限) |
| count | int    | 返回数据条数                         |

#### 查询未付款的订单

接口地址：localhost:8088/hotel/select/getUnPaidUserProduct

请求参数：

| 参数  | 是否必填 | 类型 | 说明     |
| ----- | -------- | ---- | -------- |
| page  | 是       | int  | 当前页数 |
| limit | 是       | int  | 每页条数 |

返回参数：

| 参数               | 类型   | 说明                        |
| ------------------ | ------ | --------------------------- |
| id                 | int    | 用户商品id                  |
| userId             | int    | 用户id                      |
| productId          | int    | 商品id                      |
| username           | String | 用户名                      |
| productName        | String | 商品名称                    |
| productPrice       | String | 商品单价                    |
| productNum         | int    | 商品数量                    |
| orderTime          | Date   | 下单时间                    |
| orderOverdueStates | int    | 订单是否过期(1=正常,2=过期) |
| orderStates        | int    | 支付状态(1=已支付,2=未支付) |

| 参数  | 类型   | 说明                                 |
| ----- | ------ | ------------------------------------ |
| msg   | String | 返回信息                             |
| data  | Object | 返回数据                             |
| code  | int    | 状态码(0=成功，500=失败,1000=无权限) |
| count | int    | 返回数据条数                         |

#### 根据下单时间降序排序

接口地址：localhost:8088/hotel/select/getUserProductListByOrderTimeSortDesc

请求参数：

| 参数  | 是否必填 | 类型 | 说明     |
| ----- | -------- | ---- | -------- |
| page  | 是       | int  | 当前页数 |
| limit | 是       | int  | 每页条数 |

返回参数：

| 参数               | 类型   | 说明                        |
| ------------------ | ------ | --------------------------- |
| id                 | int    | 用户商品id                  |
| userId             | int    | 用户id                      |
| productId          | int    | 商品id                      |
| username           | String | 用户名                      |
| productName        | String | 商品名称                    |
| productPrice       | String | 商品单价                    |
| productNum         | int    | 商品数量                    |
| orderTime          | Date   | 下单时间                    |
| orderOverdueStates | int    | 订单是否过期(1=正常,2=过期) |
| orderStates        | int    | 支付状态(1=已支付,2=未支付) |

| 参数  | 类型   | 说明                                 |
| ----- | ------ | ------------------------------------ |
| msg   | String | 返回信息                             |
| data  | Object | 返回数据                             |
| code  | int    | 状态码(0=成功，500=失败,1000=无权限) |
| count | int    | 返回数据条数                         |

#### 根据下单时间升序排序

接口地址：localhost:8088/hotel/select/getUserProductListByOrderTimeSortAsc

请求参数：

| 参数  | 是否必填 | 类型 | 说明     |
| ----- | -------- | ---- | -------- |
| page  | 是       | int  | 当前页数 |
| limit | 是       | int  | 每页条数 |

返回参数：

| 参数               | 类型   | 说明                        |
| ------------------ | ------ | --------------------------- |
| id                 | int    | 用户商品id                  |
| userId             | int    | 用户id                      |
| productId          | int    | 商品id                      |
| username           | String | 用户名                      |
| productName        | String | 商品名称                    |
| productPrice       | String | 商品单价                    |
| productNum         | int    | 商品数量                    |
| orderTime          | Date   | 下单时间                    |
| orderOverdueStates | int    | 订单是否过期(1=正常,2=过期) |
| orderStates        | int    | 支付状态(1=已支付,2=未支付) |

| 参数  | 类型   | 说明                                 |
| ----- | ------ | ------------------------------------ |
| msg   | String | 返回信息                             |
| data  | Object | 返回数据                             |
| code  | int    | 状态码(0=成功，500=失败,1000=无权限) |
| count | int    | 返回数据条数                         |

#### 查询某个用户的所有订单

接口地址：localhost:8088/hotel/select/getUserProductListByUserId

请求参数：

| 参数   | 是否必填 | 类型 | 说明   |
| ------ | -------- | ---- | ------ |
| userId | 是       | int  | 用户id |

返回参数：

| 参数               | 类型   | 说明                        |
| ------------------ | ------ | --------------------------- |
| id                 | int    | 用户商品id                  |
| userId             | int    | 用户id                      |
| productId          | int    | 商品id                      |
| username           | String | 用户名                      |
| productName        | String | 商品名称                    |
| productPrice       | String | 商品单价                    |
| productNum         | int    | 商品数量                    |
| orderTime          | Date   | 下单时间                    |
| orderOverdueStates | int    | 订单是否过期(1=正常,2=过期) |
| orderStates        | int    | 支付状态(1=已支付,2=未支付) |

| 参数  | 类型   | 说明                                 |
| ----- | ------ | ------------------------------------ |
| msg   | String | 返回信息                             |
| data  | Object | 返回数据                             |
| code  | int    | 状态码(0=成功，500=失败,1000=无权限) |
| count | int    | 返回数据条数                         |

#### 查询某个用户任意时间段的订单

接口地址：localhost:8088/hotel/select/getUserProductListByUserIdAndTime

请求参数：

| 参数   | 是否必填 | 类型 | 说明               |
| ------ | -------- | ---- | ------------------ |
| userId | 是       | int  | 用户id             |
| start  | 是       | Date | 开始时间(精确到天) |
| end    | 是       | Date | 结束时间(精确到天) |

返回参数：

| 参数               | 类型   | 说明                        |
| ------------------ | ------ | --------------------------- |
| id                 | int    | 用户商品id                  |
| userId             | int    | 用户id                      |
| productId          | int    | 商品id                      |
| username           | String | 用户名                      |
| productName        | String | 商品名称                    |
| productPrice       | String | 商品单价                    |
| productNum         | int    | 商品数量                    |
| orderTime          | Date   | 下单时间                    |
| orderOverdueStates | int    | 订单是否过期(1=正常,2=过期) |
| orderStates        | int    | 支付状态(1=已支付,2=未支付) |

| 参数  | 类型   | 说明                                 |
| ----- | ------ | ------------------------------------ |
| msg   | String | 返回信息                             |
| data  | Object | 返回数据                             |
| code  | int    | 状态码(0=成功，500=失败,1000=无权限) |
| count | int    | 返回数据条数                         |

#### 查询任意时间段的所有订单

接口地址：localhost:8088/hotel/select/getUserProductListByTime

请求参数：

| 参数  | 是否必填 | 类型 | 说明               |
| ----- | -------- | ---- | ------------------ |
| start | 是       | Date | 开始时间(精确到天) |
| end   | 是       | Date | 结束时间(精确到天) |

返回参数：

| 参数               | 类型   | 说明                        |
| ------------------ | ------ | --------------------------- |
| id                 | int    | 用户商品id                  |
| userId             | int    | 用户id                      |
| productId          | int    | 商品id                      |
| username           | String | 用户名                      |
| productName        | String | 商品名称                    |
| productPrice       | String | 商品单价                    |
| productNum         | int    | 商品数量                    |
| orderTime          | Date   | 下单时间                    |
| orderOverdueStates | int    | 订单是否过期(1=正常,2=过期) |
| orderStates        | int    | 支付状态(1=已支付,2=未支付) |

| 参数  | 类型   | 说明                                 |
| ----- | ------ | ------------------------------------ |
| msg   | String | 返回信息                             |
| data  | Object | 返回数据                             |
| code  | int    | 状态码(0=成功，500=失败,1000=无权限) |
| count | int    | 返回数据条数                         |

#### 统计某个用户任意时间段的消费（以天分组）

接口地址：localhost:8088/hotel/select/getUserProductPriceByUserIdDay

请求参数：

| 参数   | 是否必填 | 类型 | 说明               |
| ------ | -------- | ---- | ------------------ |
| userId | 是       | int  | 用户id             |
| start  | 是       | Date | 开始时间(精确到天) |
| end    | 是       | Date | 结束时间(精确到天) |

返回参数：

| 参数       | 类型 | 说明     |
| ---------- | ---- | -------- |
| price      | int  | 总金额   |
| order_time | Date | 精确到天 |

| 参数  | 类型   | 说明                     |
| ----- | ------ | ------------------------ |
| msg   | String | 返回信息                 |
| data  | Object | 返回数据                 |
| code  | int    | 状态码(0=成功，500=失败) |
| count | int    | 返回数据条数             |

#### 统计某个用户任意时间段的消费（以月分组）

接口地址：localhost:8088/hotel/select/getUserProductPriceByUserIdMonth

请求参数：

| 参数   | 是否必填 | 类型 | 说明               |
| ------ | -------- | ---- | ------------------ |
| userId | 是       | int  | 用户id             |
| start  | 是       | Date | 开始时间(精确到天) |
| end    | 是       | Date | 结束时间(精确到天) |

返回参数：

| 参数       | 类型 | 说明     |
| ---------- | ---- | -------- |
| price      | int  | 总金额   |
| order_time | Date | 精确到月 |

| 参数  | 类型   | 说明                     |
| ----- | ------ | ------------------------ |
| msg   | String | 返回信息                 |
| data  | Object | 返回数据                 |
| code  | int    | 状态码(0=成功，500=失败) |
| count | int    | 返回数据条数             |

#### 统计某个用户任意时间段的消费（以年分组）

接口地址：localhost:8088/hotel/select/getUserProductPriceByUserIdYear

请求参数：

| 参数   | 是否必填 | 类型 | 说明               |
| ------ | -------- | ---- | ------------------ |
| userId | 是       | int  | 用户id             |
| start  | 是       | Date | 开始时间(精确到天) |
| end    | 是       | Date | 结束时间(精确到天) |

返回参数：

| 参数       | 类型 | 说明     |
| ---------- | ---- | -------- |
| price      | int  | 总金额   |
| order_time | Date | 精确到年 |

| 参数  | 类型   | 说明                     |
| ----- | ------ | ------------------------ |
| msg   | String | 返回信息                 |
| data  | Object | 返回数据                 |
| code  | int    | 状态码(0=成功，500=失败) |
| count | int    | 返回数据条数             |

#### 统计任意时间段的总数量（以天分组）

接口地址：localhost:8088/hotel/select/getUserProductCountsByDay

请求参数：

| 参数  | 是否必填 | 类型 | 说明               |
| ----- | -------- | ---- | ------------------ |
| start | 是       | Date | 开始时间(精确到天) |
| end   | 是       | Date | 结束时间(精确到天) |

返回参数：

| 参数       | 类型 | 说明     |
| ---------- | ---- | -------- |
| counts     | int  | 总数量   |
| order_time | Date | 精确到天 |

| 参数  | 类型   | 说明                     |
| ----- | ------ | ------------------------ |
| msg   | String | 返回信息                 |
| data  | Object | 返回数据                 |
| code  | int    | 状态码(0=成功，500=失败) |
| count | int    | 返回数据条数             |

#### 统计任意时间段的总金额（以天分组）

接口地址：localhost:8088/hotel/select/getUserProductPriceByDay

请求参数：

| 参数  | 是否必填 | 类型 | 说明               |
| ----- | -------- | ---- | ------------------ |
| start | 是       | Date | 开始时间(精确到天) |
| end   | 是       | Date | 结束时间(精确到天) |

返回参数：

| 参数       | 类型 | 说明     |
| ---------- | ---- | -------- |
| price      | int  | 总金额   |
| order_time | Date | 精确到天 |

| 参数  | 类型   | 说明                     |
| ----- | ------ | ------------------------ |
| msg   | String | 返回信息                 |
| data  | Object | 返回数据                 |
| code  | int    | 状态码(0=成功，500=失败) |
| count | int    | 返回数据条数             |

#### 统计任意时间段的总数量（以月分组）

接口地址：localhost:8088/hotel/select/getUserProductCountsByMonth

请求参数：

| 参数  | 是否必填 | 类型 | 说明               |
| ----- | -------- | ---- | ------------------ |
| start | 是       | Date | 开始时间(精确到天) |
| end   | 是       | Date | 结束时间(精确到天) |

返回参数：

| 参数       | 类型 | 说明     |
| ---------- | ---- | -------- |
| counts     | int  | 总数量   |
| order_time | Date | 精确到月 |

| 参数  | 类型   | 说明                     |
| ----- | ------ | ------------------------ |
| msg   | String | 返回信息                 |
| data  | Object | 返回数据                 |
| code  | int    | 状态码(0=成功，500=失败) |
| count | int    | 返回数据条数             |

#### 统计任意时间段的总金额（以月分组）

接口地址：localhost:8088/hotel/select/getUserProductPriceByMonth

请求参数：

| 参数  | 是否必填 | 类型 | 说明               |
| ----- | -------- | ---- | ------------------ |
| start | 是       | Date | 开始时间(精确到天) |
| end   | 是       | Date | 结束时间(精确到天) |

返回参数：

| 参数       | 类型 | 说明     |
| ---------- | ---- | -------- |
| price      | int  | 总金额   |
| order_time | Date | 精确到月 |

| 参数  | 类型   | 说明                     |
| ----- | ------ | ------------------------ |
| msg   | String | 返回信息                 |
| data  | Object | 返回数据                 |
| code  | int    | 状态码(0=成功，500=失败) |
| count | int    | 返回数据条数             |

#### 统计任意时间段的总数量（以年分组）

接口地址：localhost:8088/hotel/select/getUserProductCountsByYear

请求参数：

| 参数  | 是否必填 | 类型 | 说明               |
| ----- | -------- | ---- | ------------------ |
| start | 是       | Date | 开始时间(精确到天) |
| end   | 是       | Date | 结束时间(精确到天) |

返回参数：

| 参数       | 类型 | 说明     |
| ---------- | ---- | -------- |
| counts     | int  | 总数量   |
| order_time | Date | 精确到年 |

| 参数  | 类型   | 说明                     |
| ----- | ------ | ------------------------ |
| msg   | String | 返回信息                 |
| data  | Object | 返回数据                 |
| code  | int    | 状态码(0=成功，500=失败) |
| count | int    | 返回数据条数             |

#### 统计任意时间段的总金额（以年分组）

接口地址：localhost:8088/hotel/select/getUserProductPriceByYear

请求参数：

| 参数  | 是否必填 | 类型 | 说明               |
| ----- | -------- | ---- | ------------------ |
| start | 是       | Date | 开始时间(精确到天) |
| end   | 是       | Date | 结束时间(精确到天) |

返回参数：

| 参数       | 类型 | 说明     |
| ---------- | ---- | -------- |
| price      | int  | 总金额   |
| order_time | Date | 精确到年 |

| 参数  | 类型   | 说明                     |
| ----- | ------ | ------------------------ |
| msg   | String | 返回信息                 |
| data  | Object | 返回数据                 |
| code  | int    | 状态码(0=成功，500=失败) |
| count | int    | 返回数据条数             |

#### 批量删除用户商品订单

接口地址：localhost:8088/hotel/userProduct/deleteUserProductBatch

请求参数：

| 参数 | 是否必填 | 类型 | 说明   |
| ---- | -------- | ---- | ------ |
| ids  | 是       | List | id集合 |

返回参数：

| 参数  | 类型   | 说明                                 |
| ----- | ------ | ------------------------------------ |
| msg   | String | 返回信息                             |
| data  | Object | 返回数据                             |
| code  | int    | 状态码(0=成功，500=失败,1000=无权限) |
| count | int    | 返回数据条数                         |

## 10.用户客房订单接口

#### 用户下单

接口地址：localhost:8088/hotel/userRoom/insertUserRoom

请求参数：

| 参数      | 是否必填 | 类型 | 说明     |
| --------- | -------- | ---- | -------- |
| userId    | 是       | int  | 用户id   |
| roomId    | 是       | int  | 房间id   |
| orderTime | 是       | Date | 入住时间 |
| leaveTime | 是       | Date | 离店时间 |

返回参数：

| 参数  | 类型   | 说明                                 |
| ----- | ------ | ------------------------------------ |
| msg   | String | 返回信息                             |
| data  | Object | 返回数据                             |
| code  | int    | 状态码(0=成功，500=失败,1000=无权限) |
| count | int    | 返回数据条数                         |

#### 修改订单

接口地址：localhost:8088/hotel/userRoom/updateUserRoom

请求参数：

| 参数         | 是否必填                 | 类型 | 说明               |
| ------------ | ------------------------ | ---- | ------------------ |
| id           | 是                       | int  | 用户房间id         |
| userId       | 是                       | int  | 用户id             |
| roomIdBefore | 否(若修改过房间则为必填) | int  | 修改之前的房间id   |
| roomId       | 是                       | int  | 修改之后的房间id   |
| orderTime    | 是                       | Date | 修改之后的入住时间 |
| leaveTime    | 是                       | Date | 修改之后的离店时间 |

返回参数：

| 参数  | 类型   | 说明                                 |
| ----- | ------ | ------------------------------------ |
| msg   | String | 返回信息                             |
| data  | Object | 返回数据                             |
| code  | int    | 状态码(0=成功，500=失败,1000=无权限) |
| count | int    | 返回数据条数                         |

#### 交易完成（退房）

接口地址：localhost:8088/hotel/userRoom/cancel

请求参数：

| 参数 | 是否必填 | 类型 | 说明       |
| ---- | -------- | ---- | ---------- |
| id   | 是       | int  | 用户房间id |

返回参数：

| 参数  | 类型   | 说明                                 |
| ----- | ------ | ------------------------------------ |
| msg   | String | 返回信息                             |
| data  | Object | 返回数据                             |
| code  | int    | 状态码(0=成功，500=失败,1000=无权限) |
| count | int    | 返回数据条数                         |

#### 取消订单

接口地址：localhost:8088/hotel/userRoom/deleteUserRoom

请求参数：

| 参数   | 是否必填 | 类型 | 说明       |
| ------ | -------- | ---- | ---------- |
| id     | 是       | int  | 用户房间id |
| roomId | 是       | int  | 房间id     |

返回参数：

| 参数  | 类型   | 说明                                 |
| ----- | ------ | ------------------------------------ |
| msg   | String | 返回信息                             |
| data  | Object | 返回数据                             |
| code  | int    | 状态码(0=成功，500=失败,1000=无权限) |
| count | int    | 返回数据条数                         |

#### 根据id查询订单

接口地址：localhost:8088/hotel/select/getUserRoomById

请求参数：

| 参数 | 是否必填 | 类型 | 说明       |
| ---- | -------- | ---- | ---------- |
| id   | 是       | int  | 用户房间id |

返回参数：

| 参数               | 类型   | 说明                        |
| ------------------ | ------ | --------------------------- |
| id                 | int    | 用户客房id                  |
| userId             | int    | 用户id                      |
| roomId             | int    | 房间id                      |
| username           | String | 用户名                      |
| roomNumber         | int    | 房间号                      |
| roomType           | String | 房间类型                    |
| roomPrice          | String | 房间单价                    |
| orderTime          | Date   | 下单时间                    |
| leaveTime          | Date   | 离店时间                    |
| orderOverdueStates | int    | 订单是否过期(1=正常,2=过期) |
| orderStates        | int    | 订单状态(1=已支付,2=未支付) |

| 参数  | 类型   | 说明                                 |
| ----- | ------ | ------------------------------------ |
| msg   | String | 返回信息                             |
| data  | Object | 返回数据                             |
| code  | int    | 状态码(0=成功，500=失败,1000=无权限) |
| count | int    | 返回数据条数                         |

#### 获取用户客房订单分页列表

接口地址：localhost:8088/hotel/select/getUserRoomList

请求参数：

| 参数  | 是否必填 | 类型 | 说明     |
| ----- | -------- | ---- | -------- |
| page  | 是       | int  | 当前页数 |
| limit | 是       | int  | 每页条数 |

返回参数：

| 参数               | 类型   | 说明                        |
| ------------------ | ------ | --------------------------- |
| id                 | int    | 用户客房id                  |
| userId             | int    | 用户id                      |
| roomId             | int    | 房间id                      |
| username           | String | 用户名                      |
| roomNumber         | int    | 房间号                      |
| roomType           | String | 房间类型                    |
| roomPrice          | String | 房间单价                    |
| orderTime          | Date   | 下单时间                    |
| leaveTime          | Date   | 离店时间                    |
| orderOverdueStates | int    | 订单是否过期(1=正常,2=过期) |
| orderStates        | int    | 订单状态(1=已支付,2=未支付) |

| 参数  | 类型   | 说明                                 |
| ----- | ------ | ------------------------------------ |
| msg   | String | 返回信息                             |
| data  | Object | 返回数据                             |
| code  | int    | 状态码(0=成功，500=失败,1000=无权限) |
| count | int    | 返回数据条数                         |

#### 搜索订单（模糊查询）

接口地址：localhost:8088/hotel/select/searchUserRoom

请求参数：

| 参数        | 是否必填                       | 类型   | 说明     |
| ----------- | ------------------------------ | ------ | -------- |
| orderNumber | 至少有一项必填，也可以两项都填 | String | 订单编号 |
| username    | 至少有一项必填，也可以两项都填 | String | 用户名   |

返回参数：

| 参数               | 类型   | 说明                        |
| ------------------ | ------ | --------------------------- |
| id                 | int    | 用户客房id                  |
| userId             | int    | 用户id                      |
| roomId             | int    | 房间id                      |
| username           | String | 用户名                      |
| roomNumber         | int    | 房间号                      |
| roomType           | String | 房间类型                    |
| roomPrice          | String | 房间单价                    |
| orderTime          | Date   | 下单时间                    |
| leaveTime          | Date   | 离店时间                    |
| orderOverdueStates | int    | 订单是否过期(1=正常,2=过期) |
| orderStates        | int    | 订单状态(1=已支付,2=未支付) |

| 参数  | 类型   | 说明                                 |
| ----- | ------ | ------------------------------------ |
| msg   | String | 返回信息                             |
| data  | Object | 返回数据                             |
| code  | int    | 状态码(0=成功，500=失败,1000=无权限) |
| count | int    | 返回数据条数                         |

#### 查询已付款的订单

接口地址：localhost:8088/hotel/select/getPaidUserRoom

请求参数：

| 参数  | 是否必填 | 类型 | 说明     |
| ----- | -------- | ---- | -------- |
| page  | 是       | int  | 当前页数 |
| limit | 是       | int  | 每页条数 |

返回参数：

| 参数               | 类型   | 说明                        |
| ------------------ | ------ | --------------------------- |
| id                 | int    | 用户客房id                  |
| userId             | int    | 用户id                      |
| roomId             | int    | 房间id                      |
| username           | String | 用户名                      |
| roomNumber         | int    | 房间号                      |
| roomType           | String | 房间类型                    |
| roomPrice          | String | 房间单价                    |
| orderTime          | Date   | 下单时间                    |
| leaveTime          | Date   | 离店时间                    |
| orderOverdueStates | int    | 订单是否过期(1=正常,2=过期) |
| orderStates        | int    | 订单状态(1=已支付,2=未支付) |

| 参数  | 类型   | 说明                                 |
| ----- | ------ | ------------------------------------ |
| msg   | String | 返回信息                             |
| data  | Object | 返回数据                             |
| code  | int    | 状态码(0=成功，500=失败,1000=无权限) |
| count | int    | 返回数据条数                         |

#### 查询未付款的订单

接口地址：localhost:8088/hotel/select/getUnPaidUserRoom

请求参数：

| 参数  | 是否必填 | 类型 | 说明     |
| ----- | -------- | ---- | -------- |
| page  | 是       | int  | 当前页数 |
| limit | 是       | int  | 每页条数 |

返回参数：

| 参数               | 类型   | 说明                        |
| ------------------ | ------ | --------------------------- |
| id                 | int    | 用户客房id                  |
| userId             | int    | 用户id                      |
| roomId             | int    | 房间id                      |
| username           | String | 用户名                      |
| roomNumber         | int    | 房间号                      |
| roomType           | String | 房间类型                    |
| roomPrice          | String | 房间单价                    |
| orderTime          | Date   | 下单时间                    |
| leaveTime          | Date   | 离店时间                    |
| orderOverdueStates | int    | 订单是否过期(1=正常,2=过期) |
| orderStates        | int    | 订单状态(1=已支付,2=未支付) |

| 参数  | 类型   | 说明                                 |
| ----- | ------ | ------------------------------------ |
| msg   | String | 返回信息                             |
| data  | Object | 返回数据                             |
| code  | int    | 状态码(0=成功，500=失败,1000=无权限) |
| count | int    | 返回数据条数                         |

#### 根据下单时间降序排序

接口地址：localhost:8088/hotel/select/getUserRoomListByOrderTimeSortDesc

请求参数：

| 参数  | 是否必填 | 类型 | 说明     |
| ----- | -------- | ---- | -------- |
| page  | 是       | int  | 当前页数 |
| limit | 是       | int  | 每页条数 |

返回参数：

| 参数               | 类型   | 说明                        |
| ------------------ | ------ | --------------------------- |
| id                 | int    | 用户客房id                  |
| userId             | int    | 用户id                      |
| roomId             | int    | 房间id                      |
| username           | String | 用户名                      |
| roomNumber         | int    | 房间号                      |
| roomType           | String | 房间类型                    |
| roomPrice          | String | 房间单价                    |
| orderTime          | Date   | 下单时间                    |
| leaveTime          | Date   | 离店时间                    |
| orderOverdueStates | int    | 订单是否过期(1=正常,2=过期) |
| orderStates        | int    | 订单状态(1=已支付,2=未支付) |

| 参数  | 类型   | 说明                                 |
| ----- | ------ | ------------------------------------ |
| msg   | String | 返回信息                             |
| data  | Object | 返回数据                             |
| code  | int    | 状态码(0=成功，500=失败,1000=无权限) |
| count | int    | 返回数据条数                         |

#### 根据下单时间升序排序

接口地址：localhost:8088/hotel/select/getUserRoomListByOrderTimeSortAsc

请求参数：

| 参数  | 是否必填 | 类型 | 说明     |
| ----- | -------- | ---- | -------- |
| page  | 是       | int  | 当前页数 |
| limit | 是       | int  | 每页条数 |

返回参数：

| 参数               | 类型   | 说明                        |
| ------------------ | ------ | --------------------------- |
| id                 | int    | 用户客房id                  |
| userId             | int    | 用户id                      |
| roomId             | int    | 房间id                      |
| username           | String | 用户名                      |
| roomNumber         | int    | 房间号                      |
| roomType           | String | 房间类型                    |
| roomPrice          | String | 房间单价                    |
| orderTime          | Date   | 下单时间                    |
| leaveTime          | Date   | 离店时间                    |
| orderOverdueStates | int    | 订单是否过期(1=正常,2=过期) |
| orderStates        | int    | 订单状态(1=已支付,2=未支付) |

| 参数  | 类型   | 说明                                 |
| ----- | ------ | ------------------------------------ |
| msg   | String | 返回信息                             |
| data  | Object | 返回数据                             |
| code  | int    | 状态码(0=成功，500=失败,1000=无权限) |
| count | int    | 返回数据条数                         |

#### 根据离店时间降序排序

接口地址：localhost:8088/hotel/select/getUserRoomListByLeaveTimeSortDesc

请求参数：

| 参数  | 是否必填 | 类型 | 说明     |
| ----- | -------- | ---- | -------- |
| page  | 是       | int  | 当前页数 |
| limit | 是       | int  | 每页条数 |

返回参数：

| 参数               | 类型   | 说明                        |
| ------------------ | ------ | --------------------------- |
| id                 | int    | 用户客房id                  |
| userId             | int    | 用户id                      |
| roomId             | int    | 房间id                      |
| username           | String | 用户名                      |
| roomNumber         | int    | 房间号                      |
| roomType           | String | 房间类型                    |
| roomPrice          | String | 房间单价                    |
| orderTime          | Date   | 下单时间                    |
| leaveTime          | Date   | 离店时间                    |
| orderOverdueStates | int    | 订单是否过期(1=正常,2=过期) |
| orderStates        | int    | 订单状态(1=已支付,2=未支付) |

| 参数  | 类型   | 说明                                 |
| ----- | ------ | ------------------------------------ |
| msg   | String | 返回信息                             |
| data  | Object | 返回数据                             |
| code  | int    | 状态码(0=成功，500=失败,1000=无权限) |
| count | int    | 返回数据条数                         |

#### 根据离店时间升序排序

接口地址：localhost:8088/hotel/select/getUserRoomListByLeaveTimeSortAsc

请求参数：

| 参数  | 是否必填 | 类型 | 说明     |
| ----- | -------- | ---- | -------- |
| page  | 是       | int  | 当前页数 |
| limit | 是       | int  | 每页条数 |

返回参数：

| 参数               | 类型   | 说明                        |
| ------------------ | ------ | --------------------------- |
| id                 | int    | 用户客房id                  |
| userId             | int    | 用户id                      |
| roomId             | int    | 房间id                      |
| username           | String | 用户名                      |
| roomNumber         | int    | 房间号                      |
| roomType           | String | 房间类型                    |
| roomPrice          | String | 房间单价                    |
| orderTime          | Date   | 下单时间                    |
| leaveTime          | Date   | 离店时间                    |
| orderOverdueStates | int    | 订单是否过期(1=正常,2=过期) |
| orderStates        | int    | 订单状态(1=已支付,2=未支付) |

| 参数  | 类型   | 说明                                 |
| ----- | ------ | ------------------------------------ |
| msg   | String | 返回信息                             |
| data  | Object | 返回数据                             |
| code  | int    | 状态码(0=成功，500=失败,1000=无权限) |
| count | int    | 返回数据条数                         |

#### 查询某个用户的所有订单

接口地址：localhost:8088/hotel/select/getUserRoomListByUserId

请求参数：

| 参数   | 是否必填 | 类型 | 说明   |
| ------ | -------- | ---- | ------ |
| userId | 是       | int  | 用户id |

返回参数：

| 参数               | 类型   | 说明                        |
| ------------------ | ------ | --------------------------- |
| id                 | int    | 用户客房id                  |
| userId             | int    | 用户id                      |
| roomId             | int    | 房间id                      |
| username           | String | 用户名                      |
| roomNumber         | int    | 房间号                      |
| roomType           | String | 房间类型                    |
| roomPrice          | String | 房间单价                    |
| orderTime          | Date   | 下单时间                    |
| leaveTime          | Date   | 离店时间                    |
| orderOverdueStates | int    | 订单是否过期(1=正常,2=过期) |
| orderStates        | int    | 订单状态(1=已支付,2=未支付) |

| 参数  | 类型   | 说明                                 |
| ----- | ------ | ------------------------------------ |
| msg   | String | 返回信息                             |
| data  | Object | 返回数据                             |
| code  | int    | 状态码(0=成功，500=失败,1000=无权限) |
| count | int    | 返回数据条数                         |

#### 查询某个用户任意时间段的订单

接口地址：localhost:8088/hotel/select/getUserRoomListByUserIdAndTime

请求参数：

| 参数   | 是否必填 | 类型 | 说明               |
| ------ | -------- | ---- | ------------------ |
| userId | 是       | int  | 用户id             |
| start  | 是       | Date | 开始时间(精确到天) |
| end    | 是       | Date | 结束时间(精确到天) |

返回参数：

| 参数               | 类型   | 说明                        |
| ------------------ | ------ | --------------------------- |
| id                 | int    | 用户客房id                  |
| userId             | int    | 用户id                      |
| roomId             | int    | 房间id                      |
| username           | String | 用户名                      |
| roomNumber         | int    | 房间号                      |
| roomType           | String | 房间类型                    |
| roomPrice          | String | 房间单价                    |
| orderTime          | Date   | 下单时间                    |
| leaveTime          | Date   | 离店时间                    |
| orderOverdueStates | int    | 订单是否过期(1=正常,2=过期) |
| orderStates        | int    | 订单状态(1=已支付,2=未支付) |

| 参数  | 类型   | 说明                                 |
| ----- | ------ | ------------------------------------ |
| msg   | String | 返回信息                             |
| data  | Object | 返回数据                             |
| code  | int    | 状态码(0=成功，500=失败,1000=无权限) |
| count | int    | 返回数据条数                         |

#### 查询任意时间段的所有订单

接口地址：localhost:8088/hotel/select/getUserRoomListByTime

请求参数：

| 参数  | 是否必填 | 类型 | 说明               |
| ----- | -------- | ---- | ------------------ |
| start | 是       | Date | 开始时间(精确到天) |
| end   | 是       | Date | 结束时间(精确到天) |

返回参数：

| 参数               | 类型   | 说明                        |
| ------------------ | ------ | --------------------------- |
| id                 | int    | 用户客房id                  |
| userId             | int    | 用户id                      |
| roomId             | int    | 房间id                      |
| username           | String | 用户名                      |
| roomNumber         | int    | 房间号                      |
| roomType           | String | 房间类型                    |
| roomPrice          | String | 房间单价                    |
| orderTime          | Date   | 下单时间                    |
| leaveTime          | Date   | 离店时间                    |
| orderOverdueStates | int    | 订单是否过期(1=正常,2=过期) |
| orderStates        | int    | 订单状态(1=已支付,2=未支付) |

| 参数  | 类型   | 说明                                 |
| ----- | ------ | ------------------------------------ |
| msg   | String | 返回信息                             |
| data  | Object | 返回数据                             |
| code  | int    | 状态码(0=成功，500=失败,1000=无权限) |
| count | int    | 返回数据条数                         |

#### 统计某个用户任意时间段的消费（以天分组）

接口地址：localhost:8088/hotel/select/getUserRoomPriceByUserIdDay

请求参数：

| 参数   | 是否必填 | 类型 | 说明               |
| ------ | -------- | ---- | ------------------ |
| userId | 是       | int  | 用户id             |
| start  | 是       | Date | 开始时间(精确到天) |
| end    | 是       | Date | 结束时间(精确到天) |

返回参数：

| 参数       | 类型 | 说明     |
| ---------- | ---- | -------- |
| price      | int  | 总金额   |
| order_time | Date | 精确到天 |

| 参数  | 类型   | 说明                     |
| ----- | ------ | ------------------------ |
| msg   | String | 返回信息                 |
| data  | Object | 返回数据                 |
| code  | int    | 状态码(0=成功，500=失败) |
| count | int    | 返回数据条数             |

#### 统计某个用户任意时间段的消费（以月分组）

接口地址：localhost:8088/hotel/select/getUserRoomPriceByUserIdMonth

请求参数：

| 参数   | 是否必填 | 类型 | 说明               |
| ------ | -------- | ---- | ------------------ |
| userId | 是       | int  | 用户id             |
| start  | 是       | Date | 开始时间(精确到天) |
| end    | 是       | Date | 结束时间(精确到天) |

返回参数：

| 参数       | 类型 | 说明     |
| ---------- | ---- | -------- |
| price      | int  | 总金额   |
| order_time | Date | 精确到月 |

| 参数  | 类型   | 说明                     |
| ----- | ------ | ------------------------ |
| msg   | String | 返回信息                 |
| data  | Object | 返回数据                 |
| code  | int    | 状态码(0=成功，500=失败) |
| count | int    | 返回数据条数             |

#### 统计某个用户任意时间段的消费（以年分组）

接口地址：localhost:8088/hotel/select/getUserRoomPriceByUserIdYear

请求参数：

| 参数   | 是否必填 | 类型 | 说明               |
| ------ | -------- | ---- | ------------------ |
| userId | 是       | int  | 用户id             |
| start  | 是       | Date | 开始时间(精确到天) |
| end    | 是       | Date | 结束时间(精确到天) |

返回参数：

| 参数       | 类型 | 说明     |
| ---------- | ---- | -------- |
| price      | int  | 总金额   |
| order_time | Date | 精确到年 |

| 参数  | 类型   | 说明                     |
| ----- | ------ | ------------------------ |
| msg   | String | 返回信息                 |
| data  | Object | 返回数据                 |
| code  | int    | 状态码(0=成功，500=失败) |
| count | int    | 返回数据条数             |

#### 统计任意时间段的总数量（以天分组）

接口地址：localhost:8088/hotel/select/getUserRoomCountsByDay

请求参数：

| 参数  | 是否必填 | 类型 | 说明               |
| ----- | -------- | ---- | ------------------ |
| start | 是       | Date | 开始时间(精确到天) |
| end   | 是       | Date | 结束时间(精确到天) |

返回参数：

| 参数       | 类型 | 说明     |
| ---------- | ---- | -------- |
| counts     | int  | 总数量   |
| order_time | Date | 精确到天 |

| 参数  | 类型   | 说明                     |
| ----- | ------ | ------------------------ |
| msg   | String | 返回信息                 |
| data  | Object | 返回数据                 |
| code  | int    | 状态码(0=成功，500=失败) |
| count | int    | 返回数据条数             |

#### 统计任意时间段的总金额（以天分组）

接口地址：localhost:8088/hotel/select/getUserRoomPriceByDay

请求参数：

| 参数  | 是否必填 | 类型 | 说明               |
| ----- | -------- | ---- | ------------------ |
| start | 是       | Date | 开始时间(精确到天) |
| end   | 是       | Date | 结束时间(精确到天) |

返回参数：

| 参数       | 类型 | 说明     |
| ---------- | ---- | -------- |
| price      | int  | 总金额   |
| order_time | Date | 精确到天 |

| 参数  | 类型   | 说明                     |
| ----- | ------ | ------------------------ |
| msg   | String | 返回信息                 |
| data  | Object | 返回数据                 |
| code  | int    | 状态码(0=成功，500=失败) |
| count | int    | 返回数据条数             |

#### 统计任意时间段的总数量（以月分组）

接口地址：localhost:8088/hotel/select/getUserRoomCountsByMonth

请求参数：

| 参数  | 是否必填 | 类型 | 说明               |
| ----- | -------- | ---- | ------------------ |
| start | 是       | Date | 开始时间(精确到天) |
| end   | 是       | Date | 结束时间(精确到天) |

返回参数：

| 参数       | 类型 | 说明     |
| ---------- | ---- | -------- |
| counts     | int  | 总数量   |
| order_time | Date | 精确到月 |

| 参数  | 类型   | 说明                     |
| ----- | ------ | ------------------------ |
| msg   | String | 返回信息                 |
| data  | Object | 返回数据                 |
| code  | int    | 状态码(0=成功，500=失败) |
| count | int    | 返回数据条数             |

#### 统计任意时间段的总金额（以月分组）

接口地址：localhost:8088/hotel/select/getUserRoomPriceByMonth

请求参数：

| 参数  | 是否必填 | 类型 | 说明               |
| ----- | -------- | ---- | ------------------ |
| start | 是       | Date | 开始时间(精确到天) |
| end   | 是       | Date | 结束时间(精确到天) |

返回参数：

| 参数       | 类型 | 说明     |
| ---------- | ---- | -------- |
| price      | int  | 总金额   |
| order_time | Date | 精确到月 |

| 参数  | 类型   | 说明                     |
| ----- | ------ | ------------------------ |
| msg   | String | 返回信息                 |
| data  | Object | 返回数据                 |
| code  | int    | 状态码(0=成功，500=失败) |
| count | int    | 返回数据条数             |

#### 统计任意时间段的总数量（以年分组）

接口地址：localhost:8088/hotel/select/getUserRoomCountsByYear

请求参数：

| 参数  | 是否必填 | 类型 | 说明               |
| ----- | -------- | ---- | ------------------ |
| start | 是       | Date | 开始时间(精确到天) |
| end   | 是       | Date | 结束时间(精确到天) |

返回参数：

| 参数       | 类型 | 说明     |
| ---------- | ---- | -------- |
| counts     | int  | 总数量   |
| order_time | Date | 精确到年 |

| 参数  | 类型   | 说明                     |
| ----- | ------ | ------------------------ |
| msg   | String | 返回信息                 |
| data  | Object | 返回数据                 |
| code  | int    | 状态码(0=成功，500=失败) |
| count | int    | 返回数据条数             |

#### 统计任意时间段的总金额（以年分组）

接口地址：localhost:8088/hotel/select/getUserRoomPriceByYear

请求参数：

| 参数  | 是否必填 | 类型 | 说明               |
| ----- | -------- | ---- | ------------------ |
| start | 是       | Date | 开始时间(精确到天) |
| end   | 是       | Date | 结束时间(精确到天) |

返回参数：

| 参数       | 类型 | 说明     |
| ---------- | ---- | -------- |
| price      | int  | 总金额   |
| order_time | Date | 精确到年 |

| 参数  | 类型   | 说明                     |
| ----- | ------ | ------------------------ |
| msg   | String | 返回信息                 |
| data  | Object | 返回数据                 |
| code  | int    | 状态码(0=成功，500=失败) |
| count | int    | 返回数据条数             |

#### 批量删除用户房间订单

接口地址：localhost:8088/hotel/userRoom/deleteUserRoomBatch

请求参数：

| 参数 | 是否必填 | 类型 | 说明   |
| ---- | -------- | ---- | ------ |
| ids  | 是       | List | id集合 |

返回参数：

| 参数  | 类型   | 说明                     |
| ----- | ------ | ------------------------ |
| msg   | String | 返回信息                 |
| data  | Object | 返回数据                 |
| code  | int    | 状态码(0=成功，500=失败) |
| count | int    | 返回数据条数             |

##  11.住宿记录接口

#### 修改住宿记录

接口地址：localhost:8088/hotel/accommodationRecord/updateAccommodationRecord

请求参数

| 参数       | 是否必填类型 | 类型说明 | 说明       |
| ---------- | ------------ | -------- | ---------- |
| id         | 是           | int      | 住宿记录id |
| totalPrice | 是           | String   | 总价       |

返回参数：

| 参数  | 类型   | 说明                     |
| ----- | ------ | ------------------------ |
| msg   | String | 返回信息                 |
| data  | Object | 返回数据                 |
| code  | int    | 状态码(0=成功，500=失败) |
| count | int    | 返回数据条数             |

#### 根据id删除住宿记录

接口地址：localhost:8088/hotel/accommodationRecord/deleteAccommodationRecord

请求参数：

| 参数 | 是否必填类型 | 类型说明 | 说明       |
| ---- | ------------ | -------- | ---------- |
| id   | 是           | int      | 住宿记录id |

​	返回参数：

| 参数  | 类型   | 说明                     |
| ----- | ------ | ------------------------ |
| msg   | String | 返回信息                 |
| data  | Object | 返回数据                 |
| code  | int    | 状态码(0=成功，500=失败) |
| count | int    | 返回数据条数             |

#### 批量删除用户商品订单

接口地址：localhost:8088/hotel/accommodationRecord/deleteAccommodationRecordBatch

请求参数：

| 参数 | 是否必填 | 类型 | 说明   |
| ---- | -------- | ---- | ------ |
| ids  | 是       | List | id集合 |

返回参数：

| 参数  | 类型   | 说明                                 |
| ----- | ------ | ------------------------------------ |
| msg   | String | 返回信息                             |
| data  | Object | 返回数据                             |
| code  | int    | 状态码(0=成功，500=失败,1000=无权限) |
| count | int    | 返回数据条数                         |

#### 根据id查询用户

接口地址：localhost:8088/hotel/select/getAccommodationRecordById

请求参数：

| 参数 | 是否必填类型 | 类型说明 | 说明       |
| ---- | ------------ | -------- | ---------- |
| id   | 是           | int      | 住宿记录id |

返回参数：

| 参数           | 类型   | 说明       |
| -------------- | ------ | ---------- |
| id             | int    | 住宿记录id |
| userId         | int    | 用户id     |
| roomId         | int    | 房间id     |
| username       | String | 用户名     |
| roomType       | String | 房间类型   |
| roomNumber     | int    | 房间号     |
| roomPrice      | String | 房间单价   |
| totalPrice     | String | 房间总价   |
| checkinDate    | date   | 入住日期   |
| departure_date | date   | 离开时间   |

| 参数  | 类型   | 说明                     |
| ----- | ------ | ------------------------ |
| msg   | String | 返回信息                 |
| data  | Object | 返回数据                 |
| code  | int    | 状态码(0=成功，500=失败) |
| count | int    | 返回数据条数             |

#### 根据用户名获取记录

接口地址：localhost:8088/hotel/select/getAccommodationRecordListByUsername

请求参数：

| 参数     | 是否必填类型 | 类型说明 | 说明   |
| -------- | ------------ | -------- | ------ |
| username | 是           | String   | 用户名 |

返回参数：

| 参数           | 类型   | 说明       |
| -------------- | ------ | ---------- |
| id             | int    | 住宿记录id |
| userId         | int    | 用户id     |
| roomId         | int    | 房间id     |
| username       | String | 用户名     |
| roomType       | String | 房间类型   |
| roomNumber     | int    | 房间号     |
| roomPrice      | String | 房间单价   |
| totalPrice     | String | 房间总价   |
| checkinDate    | date   | 入住日期   |
| departure_date | date   | 离开时间   |

| 参数  | 类型   | 说明                     |
| ----- | ------ | ------------------------ |
| msg   | String | 返回信息                 |
| data  | Object | 返回数据                 |
| code  | int    | 状态码(0=成功，500=失败) |
| count | int    | 返回数据条数             |

#### 获取所有记录

接口地址：localhost:8088/hotel/select/getAccommodationRecordList

请求参数：

| 参数  | 是否必填类型 | 类型说明 | 说明     |
| ----- | ------------ | -------- | -------- |
| page  | 是           | int      | 当前页数 |
| limit | 是           | int      | 每页条数 |

返回参数：

| 参数           | 类型   | 说明       |
| -------------- | ------ | ---------- |
| id             | int    | 住宿记录id |
| userId         | int    | 用户id     |
| roomId         | int    | 房间id     |
| username       | String | 用户名     |
| roomType       | String | 房间类型   |
| roomNumber     | int    | 房间号     |
| roomPrice      | String | 房间单价   |
| totalPrice     | String | 房间总价   |
| checkinDate    | date   | 入住日期   |
| departure_date | date   | 离开时间   |

| 参数  | 类型   | 说明                     |
| ----- | ------ | ------------------------ |
| msg   | String | 返回信息                 |
| data  | Object | 返回数据                 |
| code  | int    | 状态码(0=成功，500=失败) |
| count | int    | 返回数据条数             |

#### 根据入住时间降序排序

接口地址：localhost:8088/hotel/select/getAccommodationRecordListByCheckinDateSortDesc

请求参数：

| 参数  | 是否必填 | 类型 | 说明     |
| ----- | -------- | ---- | -------- |
| page  | 是       | int  | 当前页数 |
| limit | 是       | int  | 每页条数 |

返回参数：

| 参数          | 类型   | 说明       |
| ------------- | ------ | ---------- |
| id            | int    | 用户商品id |
| userId        | int    | 用户id     |
| roomId        | int    | 房间id     |
| username      | String | 用户名     |
| roomType      | String | 房间类型   |
| roomNumber    | String | 房间号     |
| roomPrice     | String | 房间价格   |
| totalPrice    | String | 总价       |
| checkinDate   | Date   | 入住时间   |
| departureDate | Date   | 离开时间   |

| 参数  | 类型   | 说明                                 |
| ----- | ------ | ------------------------------------ |
| msg   | String | 返回信息                             |
| data  | Object | 返回数据                             |
| code  | int    | 状态码(0=成功，500=失败,1000=无权限) |
| count | int    | 返回数据条数                         |

#### 根据入住时间升序排序

接口地址：localhost:8088/hotel/select/getAccommodationRecordListByCheckinDateSortAsc

请求参数：

| 参数  | 是否必填 | 类型 | 说明     |
| ----- | -------- | ---- | -------- |
| page  | 是       | int  | 当前页数 |
| limit | 是       | int  | 每页条数 |

返回参数：

| 参数          | 类型   | 说明       |
| ------------- | ------ | ---------- |
| id            | int    | 用户商品id |
| userId        | int    | 用户id     |
| roomId        | int    | 房间id     |
| username      | String | 用户名     |
| roomType      | String | 房间类型   |
| roomNumber    | String | 房间号     |
| roomPrice     | String | 房间价格   |
| totalPrice    | String | 总价       |
| checkinDate   | Date   | 入住时间   |
| departureDate | Date   | 离开时间   |

| 参数  | 类型   | 说明                                 |
| ----- | ------ | ------------------------------------ |
| msg   | String | 返回信息                             |
| data  | Object | 返回数据                             |
| code  | int    | 状态码(0=成功，500=失败,1000=无权限) |
| count | int    | 返回数据条数                         |

#### 根据离开时间降序排序

接口地址：localhost:8088/hotel/select/getAccommodationRecordListByDepartureDateSortDesc

请求参数：

| 参数  | 是否必填 | 类型 | 说明     |
| ----- | -------- | ---- | -------- |
| page  | 是       | int  | 当前页数 |
| limit | 是       | int  | 每页条数 |

返回参数：

| 参数          | 类型   | 说明       |
| ------------- | ------ | ---------- |
| id            | int    | 用户商品id |
| userId        | int    | 用户id     |
| roomId        | int    | 房间id     |
| username      | String | 用户名     |
| roomType      | String | 房间类型   |
| roomNumber    | String | 房间号     |
| roomPrice     | String | 房间价格   |
| totalPrice    | String | 总价       |
| checkinDate   | Date   | 入住时间   |
| departureDate | Date   | 离开时间   |

| 参数  | 类型   | 说明                                 |
| ----- | ------ | ------------------------------------ |
| msg   | String | 返回信息                             |
| data  | Object | 返回数据                             |
| code  | int    | 状态码(0=成功，500=失败,1000=无权限) |
| count | int    | 返回数据条数                         |

#### 根据离开时间升序排序

接口地址：localhost:8088/hotel/select/getAccommodationRecordListByDepartureDateSortAsc

请求参数：

| 参数  | 是否必填 | 类型 | 说明     |
| ----- | -------- | ---- | -------- |
| page  | 是       | int  | 当前页数 |
| limit | 是       | int  | 每页条数 |

返回参数：

| 参数          | 类型   | 说明       |
| ------------- | ------ | ---------- |
| id            | int    | 用户商品id |
| userId        | int    | 用户id     |
| roomId        | int    | 房间id     |
| username      | String | 用户名     |
| roomType      | String | 房间类型   |
| roomNumber    | String | 房间号     |
| roomPrice     | String | 房间价格   |
| totalPrice    | String | 总价       |
| checkinDate   | Date   | 入住时间   |
| departureDate | Date   | 离开时间   |

| 参数  | 类型   | 说明                                 |
| ----- | ------ | ------------------------------------ |
| msg   | String | 返回信息                             |
| data  | Object | 返回数据                             |
| code  | int    | 状态码(0=成功，500=失败,1000=无权限) |
| count | int    | 返回数据条数                         |

#### 查询某个用户的所有住房记录

接口地址：localhost:8088/hotel/select/getAccommodationRecordListByUserId

请求参数：

| 参数   | 是否必填 | 类型 | 说明   |
| ------ | -------- | ---- | ------ |
| userId | 是       | int  | 用户id |

返回参数：

| 参数          | 类型   | 说明       |
| ------------- | ------ | ---------- |
| id            | int    | 用户商品id |
| userId        | int    | 用户id     |
| roomId        | int    | 房间id     |
| username      | String | 用户名     |
| roomType      | String | 房间类型   |
| roomNumber    | String | 房间号     |
| roomPrice     | String | 房间价格   |
| totalPrice    | String | 总价       |
| checkinDate   | Date   | 入住时间   |
| departureDate | Date   | 离开时间   |

| 参数  | 类型   | 说明                                 |
| ----- | ------ | ------------------------------------ |
| msg   | String | 返回信息                             |
| data  | Object | 返回数据                             |
| code  | int    | 状态码(0=成功，500=失败,1000=无权限) |
| count | int    | 返回数据条数                         |

#### 查询某个用户任意时间段的住房记录

接口地址：localhost:8088/hotel/select/getAccommodationRecordListByUserIdAndTime

请求参数：

| 参数   | 是否必填 | 类型 | 说明               |
| ------ | -------- | ---- | ------------------ |
| userId | 是       | int  | 用户id             |
| start  | 是       | Date | 开始时间(精确到天) |
| end    | 是       | Date | 结束时间(精确到天) |

返回参数：

| 参数          | 类型   | 说明       |
| ------------- | ------ | ---------- |
| id            | int    | 用户商品id |
| userId        | int    | 用户id     |
| roomId        | int    | 房间id     |
| username      | String | 用户名     |
| roomType      | String | 房间类型   |
| roomNumber    | String | 房间号     |
| roomPrice     | String | 房间价格   |
| totalPrice    | String | 总价       |
| checkinDate   | Date   | 入住时间   |
| departureDate | Date   | 离开时间   |

| 参数  | 类型   | 说明                                 |
| ----- | ------ | ------------------------------------ |
| msg   | String | 返回信息                             |
| data  | Object | 返回数据                             |
| code  | int    | 状态码(0=成功，500=失败,1000=无权限) |
| count | int    | 返回数据条数                         |

#### 查询任意时间的所有住房记录

接口地址：localhost:8088/hotel/select/getAccommodationRecordListByTime

请求参数：

| 参数  | 是否必填 | 类型 | 说明               |
| ----- | -------- | ---- | ------------------ |
| start | 是       | Date | 开始时间(精确到天) |
| end   | 是       | Date | 结束时间(精确到天) |

返回参数：

| 参数          | 类型   | 说明       |
| ------------- | ------ | ---------- |
| id            | int    | 用户商品id |
| userId        | int    | 用户id     |
| roomId        | int    | 房间id     |
| username      | String | 用户名     |
| roomType      | String | 房间类型   |
| roomNumber    | String | 房间号     |
| roomPrice     | String | 房间价格   |
| totalPrice    | String | 总价       |
| checkinDate   | Date   | 入住时间   |
| departureDate | Date   | 离开时间   |

| 参数  | 类型   | 说明                                 |
| ----- | ------ | ------------------------------------ |
| msg   | String | 返回信息                             |
| data  | Object | 返回数据                             |
| code  | int    | 状态码(0=成功，500=失败,1000=无权限) |
| count | int    | 返回数据条数                         |

#### 统计任意时间段房间类型的总数量(以天分组)

接口地址：localhost:8088/hotel/select/getAccommodationRecordCountsByDay

请求参数：

| 参数     | 是否必填 | 类型   | 说明               |
| -------- | -------- | ------ | ------------------ |
| roomType | 是       | String | 房间类型           |
| start    | 是       | Date   | 开始时间(精确到天) |
| end      | 是       | Date   | 结束时间(精确到天) |

返回参数：

| 参数         | 类型 | 说明           |
| ------------ | ---- | -------------- |
| checkin_date | Date | 入住时间(到天) |
| counts       | int  | 房间数量       |

| 参数  | 类型   | 说明                                 |
| ----- | ------ | ------------------------------------ |
| msg   | String | 返回信息                             |
| data  | Object | 返回数据                             |
| code  | int    | 状态码(0=成功，500=失败,1000=无权限) |
| count | int    | 返回数据条数                         |

#### 统计任意时间段房间类型的总数量(以月分组)

接口地址：localhost:8088/hotel/select/getAccommodationRecordCountsByMonth

请求参数：

| 参数     | 是否必填 | 类型   | 说明               |
| -------- | -------- | ------ | ------------------ |
| roomType | 是       | String | 房间类型           |
| start    | 是       | Date   | 开始时间(精确到天) |
| end      | 是       | Date   | 结束时间(精确到天) |

返回参数：

| 参数         | 类型 | 说明           |
| ------------ | ---- | -------------- |
| checkin_date | Date | 入住时间(到月) |
| counts       | int  | 房间数量       |

| 参数  | 类型   | 说明                                 |
| ----- | ------ | ------------------------------------ |
| msg   | String | 返回信息                             |
| data  | Object | 返回数据                             |
| code  | int    | 状态码(0=成功，500=失败,1000=无权限) |
| count | int    | 返回数据条数                         |

#### 统计任意时间段房间类型的总数量(以年分组)

接口地址：localhost:8088/hotel/select/getAccommodationRecordCountsByYear

请求参数：

| 参数     | 是否必填 | 类型   | 说明               |
| -------- | -------- | ------ | ------------------ |
| roomType | 是       | String | 房间类型           |
| start    | 是       | Date   | 开始时间(精确到天) |
| end      | 是       | Date   | 结束时间(精确到天) |

返回参数：

| 参数         | 类型 | 说明           |
| ------------ | ---- | -------------- |
| checkin_date | Date | 入住时间(到年) |
| counts       | int  | 房间数量       |

| 参数  | 类型   | 说明                                 |
| ----- | ------ | ------------------------------------ |
| msg   | String | 返回信息                             |
| data  | Object | 返回数据                             |
| code  | int    | 状态码(0=成功，500=失败,1000=无权限) |
| count | int    | 返回数据条数                         |

## 12.背景图片接口

#### 添加背景

接口地址：localhost:8088/hotel/background/insertBackground

请求参数：

| 参数              | 是否必填 | 类型          | 说明     |
| ----------------- | -------- | ------------- | -------- |
| backgroundPicture | 是       | MultipartFile | 背景图片 |
| backgroundIntro   | 否       | String        | 背景简介 |

返回参数：

| 参数  | 类型   | 说明                     |
| ----- | ------ | ------------------------ |
| msg   | String | 返回信息                 |
| data  | Object | 返回数据                 |
| code  | int    | 状态码(0=成功，500=失败) |
| count | int    | 返回数据条数             |

#### 修改背景

接口地址：localhost:8088/hotel/background/updateBackground

请求参数：

| 参数              | 是否必填 | 类型   | 说明       |
| ----------------- | -------- | ------ | ---------- |
| id                | 是       | int    | 背景图片id |
| backgroundPicture | 是       | String | 背景图片   |
| backgroundIntro   | 否       | String | 背景简介   |

返回参数：

| 参数  | 类型   | 说明                     |
| ----- | ------ | ------------------------ |
| msg   | String | 返回信息                 |
| data  | Object | 返回数据                 |
| code  | int    | 状态码(0=成功，500=失败) |
| count | int    | 返回数据条数             |

#### 根据id回收图片

接口地址：localhost:8088/hotel/background/recycleBackground

请求参数：

| 参数 | 是否必填 | 类型 | 说明       |
| ---- | -------- | ---- | ---------- |
| id   | 是       | int  | 背景图片id |

返回参数：

| 参数  | 类型   | 说明                     |
| ----- | ------ | ------------------------ |
| msg   | String | 返回信息                 |
| data  | Object | 返回数据                 |
| code  | int    | 状态码(0=成功，500=失败) |
| count | int    | 返回数据条数             |

#### 将回收的图片恢复到正常状态

接口地址：localhost:8088/hotel/background/recoveryRecycleBackground

请求参数：

| 参数 | 是否必填 | 类型 | 说明       |
| ---- | -------- | ---- | ---------- |
| id   | 是       | int  | 背景图片id |

返回参数：

| 参数  | 类型   | 说明                     |
| ----- | ------ | ------------------------ |
| msg   | String | 返回信息                 |
| data  | Object | 返回数据                 |
| code  | int    | 状态码(0=成功，500=失败) |
| count | int    | 返回数据条数             |

#### 删除图片

接口地址：localhost:8088/hotel/background/deleteBackground

请求参数：

| 参数 | 是否必填 | 类型 | 说明       |
| ---- | -------- | ---- | ---------- |
| id   | 是       | int  | 背景图片id |

返回参数：

| 参数  | 类型   | 说明                     |
| ----- | ------ | ------------------------ |
| msg   | String | 返回信息                 |
| data  | Object | 返回数据                 |
| code  | int    | 状态码(0=成功，500=失败) |
| count | int    | 返回数据条数             |

#### 根据id查询图片

接口地址：localhost:8088/hotel/select/getBackgroundById

请求参数：

| 参数 | 是否必填 | 类型 | 说明       |
| ---- | -------- | ---- | ---------- |
| id   | 是       | int  | 背景图片id |

返回参数：

| 参数              | 类型   | 说明                    |
| ----------------- | ------ | ----------------------- |
| backgroundPicture | String | 背景图片                |
| backgroundIntro   | String | 背景简介                |
| createrId         | int    | 创建人id                |
| createrUsername   | String | 创建人用户名            |
| createTime        | date   | 创建时间                |
| modifierId        | int    | 修改人id                |
| modifierUsername  | String | 修改人用户名            |
| modifyTime        | date   | 修改时间                |
| existStates       | int    | 存在状态(1=正常,2=删除) |

#### 获取正常状态的图片列表

接口地址：localhost:8088/hotel/select/getBackgroundList

请求参数：

| 参数  | 是否必填类型 | 类型说明 | 说明     |
| ----- | ------------ | -------- | -------- |
| page  | 是           | int      | 当前页数 |
| limit | 是           | int      | 每页条数 |

返回参数：

| 参数              | 类型   | 说明             |
| ----------------- | ------ | ---------------- |
| backgroundPicture | String | 背景图片         |
| backgroundIntro   | String | 背景简介         |
| createrId         | int    | 创建人id         |
| createrUsername   | String | 创建人用户名     |
| createTime        | date   | 创建时间         |
| modifierId        | int    | 修改人id         |
| modifierUsername  | String | 修改人用户名     |
| modifyTime        | date   | 修改时间         |
| existStates       | int    | 存在状态(1=正常) |

#### 获取回收状态的菜单列表

接口地址：localhost:8088/hotel/select/getRecycleBackgroundList

请求参数：

| 参数  | 是否必填类型 | 类型说明 | 说明     |
| ----- | ------------ | -------- | -------- |
| page  | 是           | int      | 当前页数 |
| limit | 是           | int      | 每页条数 |

返回参数：

| 参数              | 类型   | 说明             |
| ----------------- | ------ | ---------------- |
| backgroundPicture | String | 背景图片         |
| backgroundIntro   | String | 背景简介         |
| createrId         | int    | 创建人id         |
| createrUsername   | String | 创建人用户名     |
| createTime        | date   | 创建时间         |
| modifierId        | int    | 修改人id         |
| modifierUsername  | String | 修改人用户名     |
| modifyTime        | date   | 修改时间         |
| existStates       | int    | 存在状态(2=删除) |

#### 批量回收背景图片

接口地址：localhost:8088/hotel/background/recycleBackgroundBatch

请求参数：

| 参数 | 是否必填 | 类型 | 说明   |
| ---- | -------- | ---- | ------ |
| ids  | 是       | List | id集合 |

返回参数：

| 参数  | 类型   | 说明                                 |
| ----- | ------ | ------------------------------------ |
| msg   | String | 返回信息                             |
| data  | Object | 返回数据                             |
| code  | int    | 状态码(0=成功，500=失败,1000=无权限) |
| count | int    | 返回数据条数                         |

#### 将回收的背景图片批量恢复到正常状态

接口地址：localhost:8088/hotel/background/recoveryRecycleBackgroundBatch

请求参数：

| 参数 | 是否必填 | 类型 | 说明   |
| ---- | -------- | ---- | ------ |
| ids  | 是       | List | id集合 |

返回参数：

| 参数  | 类型   | 说明                                 |
| ----- | ------ | ------------------------------------ |
| msg   | String | 返回信息                             |
| data  | Object | 返回数据                             |
| code  | int    | 状态码(0=成功，500=失败,1000=无权限) |
| count | int    | 返回数据条数                         |

#### 批量删除背景图片

接口地址：localhost:8088/hotel/background/deleteBackgroundBatch

请求参数：

| 参数 | 是否必填 | 类型 | 说明   |
| ---- | -------- | ---- | ------ |
| ids  | 是       | List | id集合 |

返回参数：

| 参数  | 类型   | 说明                                 |
| ----- | ------ | ------------------------------------ |
| msg   | String | 返回信息                             |
| data  | Object | 返回数据                             |
| code  | int    | 状态码(0=成功，500=失败,1000=无权限) |
| count | int    | 返回数据条数                         |

## 13.版权信息接口

#### 添加版权信息

接口地址：localhost:8088/hotel/copyright/insertCopyright

请求参数：

| 参数           | 是否必填 | 类型   | 说明     |
| -------------- | -------- | ------ | -------- |
| contactAddress | 否       | String | 联系地址 |
| contactNumber  | 否       | String | 联系电话 |

返回参数：

| 参数  | 类型   | 说明                     |
| ----- | ------ | ------------------------ |
| msg   | String | 返回信息                 |
| data  | Object | 返回数据                 |
| code  | int    | 状态码(0=成功，500=失败) |
| count | int    | 返回数据条数             |

#### 修改版权信息

接口地址：localhost:8088/hotel/copyright/updateCopyright

请求参数：

| 参数           | 是否必填 | 类型   | 说明       |
| -------------- | -------- | ------ | ---------- |
| id             | 是       | int    | 版权信息id |
| contactAddress | 否       | String | 联系地址   |
| contactNumber  | 否       | String | 联系电话   |

返回参数：

| 参数  | 类型   | 说明                     |
| ----- | ------ | ------------------------ |
| msg   | String | 返回信息                 |
| data  | Object | 返回数据                 |
| code  | int    | 状态码(0=成功，500=失败) |
| count | int    | 返回数据条数             |

#### 根据id回收版权信息

接口地址：localhost:8088/hotel/copyright/recycleCopyright

请求参数：

| 参数 | 是否必填 | 类型 | 说明       |
| ---- | -------- | ---- | ---------- |
| id   | 是       | int  | 版权信息id |

返回参数：

| 参数  | 类型   | 说明                     |
| ----- | ------ | ------------------------ |
| msg   | String | 返回信息                 |
| data  | Object | 返回数据                 |
| code  | int    | 状态码(0=成功，500=失败) |
| count | int    | 返回数据条数             |

#### 将回收的版权信息恢复到正常状态

接口地址：localhost:8088/hotel/copyright/recycleRecycleCopyright

请求参数：

| 参数 | 是否必填 | 类型 | 说明       |
| ---- | -------- | ---- | ---------- |
| id   | 是       | int  | 版权信息id |

返回参数：

| 参数  | 类型   | 说明                     |
| ----- | ------ | ------------------------ |
| msg   | String | 返回信息                 |
| data  | Object | 返回数据                 |
| code  | int    | 状态码(0=成功，500=失败) |
| count | int    | 返回数据条数             |

#### 删除版权信息

接口地址：localhost:8088/hotel/copyright/deleteCopyright

请求参数：

| 参数 | 是否必填 | 类型 | 说明       |
| ---- | -------- | ---- | ---------- |
| id   | 是       | int  | 版权信息id |

返回参数：

| 参数  | 类型   | 说明                     |
| ----- | ------ | ------------------------ |
| msg   | String | 返回信息                 |
| data  | Object | 返回数据                 |
| code  | int    | 状态码(0=成功，500=失败) |
| count | int    | 返回数据条数             |

#### 根据id查询版权信息

接口地址：localhost:8088/hotel/select/getCopyrightById

请求参数：

| 参数 | 是否必填 | 类型 | 说明       |
| ---- | -------- | ---- | ---------- |
| id   | 是       | int  | 版权信息id |

返回参数：

| 参数             | 类型   | 说明                    |
| ---------------- | ------ | ----------------------- |
| id               | int    | 版权信息id              |
| contactAddress   | String | 联系地址                |
| contactNumber    | String | 联系电话                |
| createrId        | int    | 创建人id                |
| createrUsername  | String | 创建人用户名            |
| createTime       | date   | 创建时间                |
| modifierId       | int    | 修改人id                |
| modifierUsername | String | 修改人用户名            |
| modifyTime       | date   | 修改时间                |
| existStates      | int    | 存在状态(1=正常,2=删除) |

#### 获取正常状态的版权信息列表

接口地址：localhost:8088/hotel/select/getCopyrightList

请求参数：

| 参数  | 是否必填类型 | 类型说明 | 说明     |
| ----- | ------------ | -------- | -------- |
| page  | 是           | int      | 当前页数 |
| limit | 是           | int      | 每页条数 |

返回参数：

| 参数             | 类型   | 说明                    |
| ---------------- | ------ | ----------------------- |
| id               | int    | 版权信息id              |
| contactAddress   | String | 联系地址                |
| contactNumber    | String | 联系电话                |
| createrId        | int    | 创建人id                |
| createrUsername  | String | 创建人用户名            |
| createTime       | date   | 创建时间                |
| modifierId       | int    | 修改人id                |
| modifierUsername | String | 修改人用户名            |
| modifyTime       | date   | 修改时间                |
| existStates      | int    | 存在状态(1=正常,2=删除) |

#### 获取回收状态的版权信息列表

接口地址：localhost:8088/hotel/select/getRecycleCopyrightList

请求参数：

| 参数  | 是否必填类型 | 类型说明 | 说明     |
| ----- | ------------ | -------- | -------- |
| page  | 是           | int      | 当前页数 |
| limit | 是           | int      | 每页条数 |

返回参数：

| 参数             | 类型   | 说明                    |
| ---------------- | ------ | ----------------------- |
| id               | int    | 版权信息id              |
| contactAddress   | String | 联系地址                |
| contactNumber    | String | 联系电话                |
| createrId        | int    | 创建人id                |
| createrUsername  | String | 创建人用户名            |
| createTime       | date   | 创建时间                |
| modifierId       | int    | 修改人id                |
| modifierUsername | String | 修改人用户名            |
| modifyTime       | date   | 修改时间                |
| existStates      | int    | 存在状态(1=正常,2=删除) |

#### 批量回收版权信息

接口地址：localhost:8088/hotel/copyright/recycleCopyrightBatch

请求参数：

| 参数 | 是否必填 | 类型 | 说明   |
| ---- | -------- | ---- | ------ |
| ids  | 是       | List | id集合 |

返回参数：

| 参数  | 类型   | 说明                                 |
| ----- | ------ | ------------------------------------ |
| msg   | String | 返回信息                             |
| data  | Object | 返回数据                             |
| code  | int    | 状态码(0=成功，500=失败,1000=无权限) |
| count | int    | 返回数据条数                         |

#### 将回收的版权信息批量恢复到正常状态

接口地址：localhost:8088/hotel/copyright/recoveryRecycleCopyrightBatch

请求参数：

| 参数 | 是否必填 | 类型 | 说明   |
| ---- | -------- | ---- | ------ |
| ids  | 是       | List | id集合 |

返回参数：

| 参数  | 类型   | 说明                                 |
| ----- | ------ | ------------------------------------ |
| msg   | String | 返回信息                             |
| data  | Object | 返回数据                             |
| code  | int    | 状态码(0=成功，500=失败,1000=无权限) |
| count | int    | 返回数据条数                         |

#### 批量删除版权信息

接口地址：localhost:8088/hotel/copyright/deleteCopyrightBatch

请求参数：

| 参数 | 是否必填 | 类型 | 说明   |
| ---- | -------- | ---- | ------ |
| ids  | 是       | List | id集合 |

返回参数：

| 参数  | 类型   | 说明                                 |
| ----- | ------ | ------------------------------------ |
| msg   | String | 返回信息                             |
| data  | Object | 返回数据                             |
| code  | int    | 状态码(0=成功，500=失败,1000=无权限) |
| count | int    | 返回数据条数                         |

## 14.优惠券接口

#### 添加优惠券

接口地址：localhost:8088/hotel/coupon/insertCoupon

请求参数：

| 参数              | 是否必填 | 类型          | 说明           |
| ----------------- | -------- | ------------- | -------------- |
| couponPicture     | 是       | MultipartFile | 优惠券图片     |
| couponDetails     | 否       | String        | 优惠券详情     |
| couponPrice       | 是       | String        | 优惠券价格     |
| usableRange       | 是       | String        | 优惠券使用范围 |
| serviceConditions | 是       | String        | 优惠券使用条件 |
| expirationDate    | 是       | date          | 到期时间       |

返回参数：

| 参数  | 类型   | 说明                     |
| ----- | ------ | ------------------------ |
| msg   | String | 返回信息                 |
| data  | Object | 返回数据                 |
| code  | int    | 状态码(0=成功，500=失败) |
| count | int    | 返回数据条数             |

#### 修改优惠券

接口地址：localhost:8088/hotel/coupon/updateCoupon

请求参数：

| 参数              | 是否必填 | 类型          | 说明                        |
| ----------------- | -------- | ------------- | --------------------------- |
| id                | 是       | int           | 优惠券id                    |
| couponPicture     | 否       | MultipartFile | 优惠券图片                  |
| couponDetails     | 否       | String        | 优惠券详情                  |
| couponPrice       | 否       | String        | 优惠券价格                  |
| usableRange       | 否       | String        | 优惠券使用范围              |
| serviceConditions | 否       | String        | 优惠券使用条件              |
| expirationDate    | 否       | date          | 到期时间                    |
| usageStates       | 否       | int           | 使用状态(1=未使用,2=已使用) |

返回参数：

| 参数  | 类型   | 说明                     |
| ----- | ------ | ------------------------ |
| msg   | String | 返回信息                 |
| data  | Object | 返回数据                 |
| code  | int    | 状态码(0=成功，500=失败) |
| count | int    | 返回数据条数             |

#### 根据id回收优惠券

接口地址：localhost:8088/hotel/coupon/recycleCoupon

请求参数：

| 参数 | 是否必填 | 类型 | 说明     |
| ---- | -------- | ---- | -------- |
| id   | 是       | int  | 优惠券id |

返回参数：

| 参数  | 类型   | 说明                     |
| ----- | ------ | ------------------------ |
| msg   | String | 返回信息                 |
| data  | Object | 返回数据                 |
| code  | int    | 状态码(0=成功，500=失败) |
| count | int    | 返回数据条数             |

#### 将回收的优惠券恢复到正常状态

接口地址：localhost:8088/hotel/coupon/recoveryRecycleCoupon

请求参数：

| 参数 | 是否必填 | 类型 | 说明     |
| ---- | -------- | ---- | -------- |
| id   | 是       | int  | 优惠券id |

返回参数：

| 参数  | 类型   | 说明                     |
| ----- | ------ | ------------------------ |
| msg   | String | 返回信息                 |
| data  | Object | 返回数据                 |
| code  | int    | 状态码(0=成功，500=失败) |
| count | int    | 返回数据条数             |

#### 删除优惠券

接口地址：localhost:8088/hotel/coupon/deleteCoupon

请求参数：

| 参数 | 是否必填 | 类型 | 说明     |
| ---- | -------- | ---- | -------- |
| id   | 是       | int  | 优惠券id |

返回参数：

| 参数  | 类型   | 说明                     |
| ----- | ------ | ------------------------ |
| msg   | String | 返回信息                 |
| data  | Object | 返回数据                 |
| code  | int    | 状态码(0=成功，500=失败) |
| count | int    | 返回数据条数             |

#### 根据id查询优惠券

接口地址：localhost:8088/hotel/select/getCouponById

请求参数：

| 参数 | 是否必填 | 类型 | 说明     |
| ---- | -------- | ---- | -------- |
| id   | 是       | int  | 优惠券id |

返回参数：

| 参数              | 类型   | 说明                        |
| ----------------- | ------ | --------------------------- |
| id                | int    | 优惠券id                    |
| userId            | int    | 用户id                      |
| username          | String | 用户名                      |
| couponPicture     | int    | 优惠券图片                  |
| couponPrice       | String | 优惠券价格                  |
| couponDetails     | String | 优惠券详情                  |
| usableRange       | String | 使用范围                    |
| serviceConditions | String | 使用条件                    |
| getDate           | date   | 领取日期                    |
| expirationDate    | date   | 到期日期                    |
| couponStates      | int    | 优惠券状态(1=正常,2=删除)   |
| usageStates       | int    | 使用状态(1=未使用,2=已使用) |

#### 获取正常状态的优惠券列表

接口地址：localhost:8088/hotel/select/getCouponList

请求参数：

| 参数        | 是否必填类型 | 类型说明 | 说明                        |
| ----------- | ------------ | -------- | --------------------------- |
| page        | 是           | int      | 当前页数                    |
| limit       | 是           | int      | 每页条数                    |
| usageStates | 否           | int      | 使用状态(1=未使用,2=已使用) |

返回参数：

| 参数              | 类型   | 说明                        |
| ----------------- | ------ | --------------------------- |
| id                | int    | 优惠券id                    |
| userId            | int    | 用户id                      |
| username          | String | 用户名                      |
| couponPicture     | int    | 优惠券图片                  |
| couponPrice       | String | 优惠券价格                  |
| couponDetails     | String | 优惠券详情                  |
| usableRange       | String | 使用范围                    |
| serviceConditions | String | 使用条件                    |
| getDate           | date   | 领取日期                    |
| expirationDate    | date   | 到期日期                    |
| couponStates      | int    | 优惠券状态(1=正常,2=删除)   |
| usageStates       | int    | 使用状态(1=未使用,2=已使用) |

#### 获取回收状态的优惠券列表

接口地址：localhost:8088/hotel/select/getRecycleCouponList

请求参数：

| 参数        | 是否必填类型 | 类型说明 | 说明                        |
| ----------- | ------------ | -------- | --------------------------- |
| page        | 是           | int      | 当前页数                    |
| limit       | 是           | int      | 每页条数                    |
| usageStates | 否           | int      | 使用状态(1=未使用,2=已使用) |

返回参数：

| 参数              | 类型   | 说明                        |
| ----------------- | ------ | --------------------------- |
| id                | int    | 优惠券id                    |
| userId            | int    | 用户id                      |
| username          | String | 用户名                      |
| couponPicture     | int    | 优惠券图片                  |
| couponPrice       | String | 优惠券价格                  |
| couponDetails     | String | 优惠券详情                  |
| usableRange       | String | 使用范围                    |
| serviceConditions | String | 使用条件                    |
| getDate           | date   | 领取日期                    |
| expirationDate    | date   | 到期日期                    |
| couponStates      | int    | 优惠券状态(1=正常,2=删除)   |
| usageStates       | int    | 使用状态(1=未使用,2=已使用) |

#### 批量回收优惠券

接口地址：localhost:8088/hotel/coupon/recycleCouponBatch

请求参数：

| 参数 | 是否必填 | 类型 | 说明   |
| ---- | -------- | ---- | ------ |
| ids  | 是       | List | id集合 |

返回参数：

| 参数  | 类型   | 说明                                 |
| ----- | ------ | ------------------------------------ |
| msg   | String | 返回信息                             |
| data  | Object | 返回数据                             |
| code  | int    | 状态码(0=成功，500=失败,1000=无权限) |
| count | int    | 返回数据条数                         |

#### 将回收的优惠券批量恢复到正常状态

接口地址：localhost:8088/hotel/coupon/recoveryRecycleCouponBatch

请求参数：

| 参数 | 是否必填 | 类型 | 说明   |
| ---- | -------- | ---- | ------ |
| ids  | 是       | List | id集合 |

返回参数：

| 参数  | 类型   | 说明                                 |
| ----- | ------ | ------------------------------------ |
| msg   | String | 返回信息                             |
| data  | Object | 返回数据                             |
| code  | int    | 状态码(0=成功，500=失败,1000=无权限) |
| count | int    | 返回数据条数                         |

#### 批量删除优惠券

接口地址：localhost:8088/hotel/coupon/deleteCouponBatch

请求参数：

| 参数 | 是否必填 | 类型 | 说明   |
| ---- | -------- | ---- | ------ |
| ids  | 是       | List | id集合 |

返回参数：

| 参数  | 类型   | 说明                                 |
| ----- | ------ | ------------------------------------ |
| msg   | String | 返回信息                             |
| data  | Object | 返回数据                             |
| code  | int    | 状态码(0=成功，500=失败,1000=无权限) |
| count | int    | 返回数据条数                         |

## 15.消费记录接口

#### 修改消费记录

接口地址：localhost:8088/hotel/expense/updateExpense

请求参数：

| 参数              | 是否必填 | 类型   | 说明       |
| ----------------- | -------- | ------ | ---------- |
| id                | 是       | int    | 消费记录id |
| consumptionType   | 是       | String | 消费类型   |
| productName       | 是       | String | 商品名称   |
| quantity          | 是       | String | 购买数量   |
| tradingManner     | 是       | String | 交易方式   |
| transactionAmount | 是       | String | 交易金额   |

返回参数：

| 参数  | 类型   | 说明                     |
| ----- | ------ | ------------------------ |
| msg   | String | 返回信息                 |
| data  | Object | 返回数据                 |
| code  | int    | 状态码(0=成功，500=失败) |
| count | int    | 返回数据条数             |

#### 删除消费记录

接口地址：localhost:8088/hotel/expense/deleteExpense

请求参数：

| 参数 | 是否必填 | 类型 | 说明       |
| ---- | -------- | ---- | ---------- |
| id   | 是       | int  | 消费记录id |

返回参数：

| 参数  | 类型   | 说明                     |
| ----- | ------ | ------------------------ |
| msg   | String | 返回信息                 |
| data  | Object | 返回数据                 |
| code  | int    | 状态码(0=成功，500=失败) |
| count | int    | 返回数据条数             |

#### 根据id查询消费记录

接口地址：localhost:8088/hotel/select/getExpenseById

请求参数：

| 参数 | 是否必填 | 类型 | 说明       |
| ---- | -------- | ---- | ---------- |
| id   | 是       | int  | 消费记录id |

返回参数：

| 参数              | 类型   | 说明       |
| ----------------- | ------ | ---------- |
| id                | int    | 消费记录id |
| userId            | int    | 用户id     |
| username          | int    | 用户名     |
| consumptionType   | String | 消费类型   |
| productName       | String | 商品名称   |
| quantity          | String | 购买数量   |
| tradingManner     | String | 交易方式   |
| transactionAmount | String | 交易金额   |
| consumptionDate   | date   | 消费日期   |

#### 获取消费记录列表

接口地址：localhost:8088/hotel/select/getExpenseList

请求参数：

| 参数  | 是否必填类型 | 类型说明 | 说明     |
| ----- | ------------ | -------- | -------- |
| page  | 是           | int      | 当前页数 |
| limit | 是           | int      | 每页条数 |

返回参数：

| 参数              | 类型   | 说明       |
| ----------------- | ------ | ---------- |
| id                | int    | 消费记录id |
| userId            | int    | 用户id     |
| username          | int    | 用户名     |
| consumptionType   | String | 消费类型   |
| productName       | String | 商品名称   |
| quantity          | String | 购买数量   |
| tradingManner     | String | 交易方式   |
| transactionAmount | String | 交易金额   |
| consumptionDate   | date   | 消费日期   |

#### 批量删除用户商品订单

接口地址：localhost:8088/hotel/expense/deleteExpenseBatch

请求参数：

| 参数 | 是否必填 | 类型 | 说明   |
| ---- | -------- | ---- | ------ |
| ids  | 是       | List | id集合 |

返回参数：

| 参数  | 类型   | 说明                                 |
| ----- | ------ | ------------------------------------ |
| msg   | String | 返回信息                             |
| data  | Object | 返回数据                             |
| code  | int    | 状态码(0=成功，500=失败,1000=无权限) |
| count | int    | 返回数据条数                         |

#### 搜索消费记录

接口地址：localhost:8088/hotel/select/deleteExpenseBatch

请求参数：

| 参数 | 是否必填 | 类型 | 说明   |
| ---- | -------- | ---- | ------ |
| ids  | 是       | List | id集合 |

返回参数：

| 参数  | 类型   | 说明                                 |
| ----- | ------ | ------------------------------------ |
| msg   | String | 返回信息                             |
| data  | Object | 返回数据                             |
| code  | int    | 状态码(0=成功，500=失败,1000=无权限) |
| count | int    | 返回数据条数                         |

#### 根据消费时间降序排序

接口地址：localhost:8088/hotel/select/getExpenseListByConsumptionDateSortDesc

请求参数：

| 参数  | 是否必填 | 类型 | 说明     |
| ----- | -------- | ---- | -------- |
| page  | 是       | int  | 当前页数 |
| limit | 是       | int  | 每页条数 |

返回参数：

| 参数              | 类型     | 说明       |
| ----------------- | -------- | ---------- |
| id                | int      | 消费记录id |
| userId            | int      | 用户id     |
| username          | String   | 用户名     |
| orderNumber       | String   | 订单编号   |
| consumptionType   | String   | 消费类型   |
| productName       | String   | 商品名称   |
| quantity          | String   | 数量       |
| tradingManner     | String   | 交易方式   |
| transactionAmount | String   | 交易金额   |
| consumptionDate   | datetime | 消费日期   |

| 参数  | 类型   | 说明                                 |
| ----- | ------ | ------------------------------------ |
| msg   | String | 返回信息                             |
| data  | Object | 返回数据                             |
| code  | int    | 状态码(0=成功，500=失败,1000=无权限) |
| count | int    | 返回数据条数                         |

#### 根据消费时间升序排序

接口地址：localhost:8088/hotel/select/getExpenseListByConsumptionDateSortAsc

请求参数：

| 参数  | 是否必填 | 类型 | 说明     |
| ----- | -------- | ---- | -------- |
| page  | 是       | int  | 当前页数 |
| limit | 是       | int  | 每页条数 |

返回参数：

| 参数              | 类型     | 说明       |
| ----------------- | -------- | ---------- |
| id                | int      | 消费记录id |
| userId            | int      | 用户id     |
| username          | String   | 用户名     |
| orderNumber       | String   | 订单编号   |
| consumptionType   | String   | 消费类型   |
| productName       | String   | 商品名称   |
| quantity          | String   | 数量       |
| tradingManner     | String   | 交易方式   |
| transactionAmount | String   | 交易金额   |
| consumptionDate   | datetime | 消费日期   |

| 参数  | 类型   | 说明                                 |
| ----- | ------ | ------------------------------------ |
| msg   | String | 返回信息                             |
| data  | Object | 返回数据                             |
| code  | int    | 状态码(0=成功，500=失败,1000=无权限) |
| count | int    | 返回数据条数                         |

#### 查询某个用户的所有消费记录

接口地址：localhost:8088/hotel/select/getExpenseListByUserId

请求参数：

| 参数   | 是否必填 | 类型 | 说明   |
| ------ | -------- | ---- | ------ |
| userId | 是       | int  | 用户id |

返回参数：

| 参数              | 类型     | 说明       |
| ----------------- | -------- | ---------- |
| id                | int      | 消费记录id |
| userId            | int      | 用户id     |
| username          | String   | 用户名     |
| orderNumber       | String   | 订单编号   |
| consumptionType   | String   | 消费类型   |
| productName       | String   | 商品名称   |
| quantity          | String   | 数量       |
| tradingManner     | String   | 交易方式   |
| transactionAmount | String   | 交易金额   |
| consumptionDate   | datetime | 消费日期   |

| 参数  | 类型   | 说明                                 |
| ----- | ------ | ------------------------------------ |
| msg   | String | 返回信息                             |
| data  | Object | 返回数据                             |
| code  | int    | 状态码(0=成功，500=失败,1000=无权限) |
| count | int    | 返回数据条数                         |

#### 查询某个用户任意时间的消费记录

接口地址：localhost:8088/hotel/select/getExpenseListByUserIdAndTime

请求参数：

| 参数   | 是否必填 | 类型 | 说明               |
| ------ | -------- | ---- | ------------------ |
| userId | 是       | int  | 用户id             |
| start  | 是       | Date | 开始时间(精确到天) |
| end    | 是       | Date | 结束时间(精确到天) |

返回参数：

| 参数              | 类型     | 说明       |
| ----------------- | -------- | ---------- |
| id                | int      | 消费记录id |
| userId            | int      | 用户id     |
| username          | String   | 用户名     |
| orderNumber       | String   | 订单编号   |
| consumptionType   | String   | 消费类型   |
| productName       | String   | 商品名称   |
| quantity          | String   | 数量       |
| tradingManner     | String   | 交易方式   |
| transactionAmount | String   | 交易金额   |
| consumptionDate   | datetime | 消费日期   |

| 参数  | 类型   | 说明                                 |
| ----- | ------ | ------------------------------------ |
| msg   | String | 返回信息                             |
| data  | Object | 返回数据                             |
| code  | int    | 状态码(0=成功，500=失败,1000=无权限) |
| count | int    | 返回数据条数                         |

#### 查询任意时间段的所有消费记录

接口地址：localhost:8088/hotel/select/getExpenseListByTime

请求参数：

| 参数  | 是否必填 | 类型 | 说明               |
| ----- | -------- | ---- | ------------------ |
| start | 是       | Date | 开始时间(精确到天) |
| end   | 是       | Date | 结束时间(精确到天) |

返回参数：

| 参数              | 类型     | 说明       |
| ----------------- | -------- | ---------- |
| id                | int      | 消费记录id |
| userId            | int      | 用户id     |
| username          | String   | 用户名     |
| orderNumber       | String   | 订单编号   |
| consumptionType   | String   | 消费类型   |
| productName       | String   | 商品名称   |
| quantity          | String   | 数量       |
| tradingManner     | String   | 交易方式   |
| transactionAmount | String   | 交易金额   |
| consumptionDate   | datetime | 消费日期   |

| 参数  | 类型   | 说明                                 |
| ----- | ------ | ------------------------------------ |
| msg   | String | 返回信息                             |
| data  | Object | 返回数据                             |
| code  | int    | 状态码(0=成功，500=失败,1000=无权限) |
| count | int    | 返回数据条数                         |

#### 统计某个物品某个时间段的消费数量(以天分组)

接口地址：localhost:8088/hotel/select/getExpenseQuantityByDay

请求参数：

| 参数        | 是否必填 | 类型   | 说明               |
| ----------- | -------- | ------ | ------------------ |
| productName | 是       | String | 物品名称           |
| start       | 是       | Date   | 开始时间(精确到天) |
| end         | 是       | Date   | 结束时间(精确到天) |

返回参数：

| 参数             | 类型   | 说明           |
| ---------------- | ------ | -------------- |
| totalQuantity    | String | 某个物品的数量 |
| consumption_date | date   | 消费日期(到天) |

| 参数  | 类型   | 说明                                 |
| ----- | ------ | ------------------------------------ |
| msg   | String | 返回信息                             |
| data  | Object | 返回数据                             |
| code  | int    | 状态码(0=成功，500=失败,1000=无权限) |
| count | int    | 返回数据条数                         |

#### 统计某个物品某个时间段的消费数量(以月分组)

接口地址：localhost:8088/hotel/select/getExpenseQuantityByMonth

请求参数：

| 参数        | 是否必填 | 类型   | 说明               |
| ----------- | -------- | ------ | ------------------ |
| productName | 是       | String | 物品名称           |
| start       | 是       | Date   | 开始时间(精确到天) |
| end         | 是       | Date   | 结束时间(精确到天) |

返回参数：

| 参数             | 类型   | 说明           |
| ---------------- | ------ | -------------- |
| totalQuantity    | String | 某个物品的数量 |
| consumption_date | date   | 消费日期(到月) |

| 参数  | 类型   | 说明                                 |
| ----- | ------ | ------------------------------------ |
| msg   | String | 返回信息                             |
| data  | Object | 返回数据                             |
| code  | int    | 状态码(0=成功，500=失败,1000=无权限) |
| count | int    | 返回数据条数                         |

#### 统计某个物品某个时间段的消费数量(以年分组)

接口地址：localhost:8088/hotel/select/getExpenseQuantityByYear

请求参数：

| 参数        | 是否必填 | 类型   | 说明               |
| ----------- | -------- | ------ | ------------------ |
| productName | 是       | String | 物品名称           |
| start       | 是       | Date   | 开始时间(精确到天) |
| end         | 是       | Date   | 结束时间(精确到天) |

返回参数：

| 参数             | 类型   | 说明           |
| ---------------- | ------ | -------------- |
| totalQuantity    | String | 某个物品的数量 |
| consumption_date | date   | 消费日期(到年) |

| 参数  | 类型   | 说明                                 |
| ----- | ------ | ------------------------------------ |
| msg   | String | 返回信息                             |
| data  | Object | 返回数据                             |
| code  | int    | 状态码(0=成功，500=失败,1000=无权限) |
| count | int    | 返回数据条数                         |

## 16.酒店特色接口

#### 添加酒店特色

接口地址：localhost:8088/hotel/features/insertFeatures

请求参数：

| 参数           | 是否必填 | 类型          | 说明     |
| -------------- | -------- | ------------- | -------- |
| featurePicture | 是       | MultipartFile | 特色图片 |
| featureIntro   | 否       | String        | 特色简介 |

返回参数：

| 参数  | 类型   | 说明                     |
| ----- | ------ | ------------------------ |
| msg   | String | 返回信息                 |
| data  | Object | 返回数据                 |
| code  | int    | 状态码(0=成功，500=失败) |
| count | int    | 返回数据条数             |

#### 修改酒店特色

接口地址：localhost:8088/hotel/features/updateFeatures

请求参数：

| 参数           | 是否必填 | 类型          | 说明     |
| -------------- | -------- | ------------- | -------- |
| id             | 是       | int           | 特色id   |
| featurePicture | 是       | MultipartFile | 特色图片 |
| featureIntro   | 否       | String        | 特色简介 |

返回参数：

| 参数  | 类型   | 说明                     |
| ----- | ------ | ------------------------ |
| msg   | String | 返回信息                 |
| data  | Object | 返回数据                 |
| code  | int    | 状态码(0=成功，500=失败) |
| count | int    | 返回数据条数             |

#### 根据id回收酒店特色

接口地址：localhost:8088/hotel/features/recycleFeatures

请求参数：

| 参数 | 是否必填 | 类型 | 说明   |
| ---- | -------- | ---- | ------ |
| id   | 是       | int  | 特色id |

返回参数：

| 参数  | 类型   | 说明                     |
| ----- | ------ | ------------------------ |
| msg   | String | 返回信息                 |
| data  | Object | 返回数据                 |
| code  | int    | 状态码(0=成功，500=失败) |
| count | int    | 返回数据条数             |

#### 将回收的酒店特色恢复到正常状态

接口地址：http://localhost:8088/hotel/features/recoveryRecycleFeatures

请求参数：

| 参数 | 是否必填 | 类型 | 说明   |
| ---- | -------- | ---- | ------ |
| id   | 是       | int  | 特色id |

返回参数：

| 参数  | 类型   | 说明                     |
| ----- | ------ | ------------------------ |
| msg   | String | 返回信息                 |
| data  | Object | 返回数据                 |
| code  | int    | 状态码(0=成功，500=失败) |
| count | int    | 返回数据条数             |

#### 删除酒店特色

接口地址：localhost:8088/hotel/features/deleteFeatures

请求参数：

| 参数 | 是否必填 | 类型 | 说明   |
| ---- | -------- | ---- | ------ |
| id   | 是       | int  | 特色id |

返回参数：

| 参数  | 类型   | 说明                     |
| ----- | ------ | ------------------------ |
| msg   | String | 返回信息                 |
| data  | Object | 返回数据                 |
| code  | int    | 状态码(0=成功，500=失败) |
| count | int    | 返回数据条数             |

#### 根据id查询酒店特色

接口地址：localhost:8088/hotel/select/getFeaturesById

请求参数：

| 参数 | 是否必填 | 类型 | 说明   |
| ---- | -------- | ---- | ------ |
| id   | 是       | int  | 特色id |

返回参数：

| 参数             | 类型   | 说明                    |
| ---------------- | ------ | ----------------------- |
| featurePicture   | String | 特色图片                |
| featureIntro     | String | 特色简介                |
| createrId        | int    | 创建人id                |
| createrUsername  | int    | 创建人用户名            |
| createTime       | date   | 创建时间                |
| modifierId       | int    | 修改人id                |
| modifierUsername | String | 修改人用户名            |
| modifyTime       | date   | 修改时间                |
| existStates      | int    | 存在状态(1=正常,2=删除) |

#### 获取正常状态的酒店特色列表

接口地址：localhost:8088/hotel/select/getFeaturesList

请求参数：

| 参数  | 是否必填类型 | 类型说明 | 说明     |
| ----- | ------------ | -------- | -------- |
| page  | 是           | int      | 当前页数 |
| limit | 是           | int      | 每页条数 |

返回参数：

| 参数             | 类型   | 说明                    |
| ---------------- | ------ | ----------------------- |
| featurePicture   | String | 特色图片                |
| featureIntro     | String | 特色简介                |
| createrId        | int    | 创建人id                |
| createrUsername  | int    | 创建人用户名            |
| createTime       | date   | 创建时间                |
| modifierId       | int    | 修改人id                |
| modifierUsername | String | 修改人用户名            |
| modifyTime       | date   | 修改时间                |
| existStates      | int    | 存在状态(1=正常,2=删除) |

#### 获取回收状态的酒店特色列表

接口地址：localhost:8088/hotel/select/getRecycleFeaturesList

请求参数：

| 参数  | 是否必填类型 | 类型说明 | 说明     |
| ----- | ------------ | -------- | -------- |
| page  | 是           | int      | 当前页数 |
| limit | 是           | int      | 每页条数 |

返回参数：

| 参数             | 类型   | 说明                    |
| ---------------- | ------ | ----------------------- |
| featurePicture   | String | 特色图片                |
| featureIntro     | String | 特色简介                |
| createrId        | int    | 创建人id                |
| createrUsername  | int    | 创建人用户名            |
| createTime       | date   | 创建时间                |
| modifierId       | int    | 修改人id                |
| modifierUsername | String | 修改人用户名            |
| modifyTime       | date   | 修改时间                |
| existStates      | int    | 存在状态(1=正常,2=删除) |

#### 批量回收酒店特色

接口地址：localhost:8088/hotel/features/recycleFeaturesBatch

请求参数：

| 参数 | 是否必填 | 类型 | 说明   |
| ---- | -------- | ---- | ------ |
| ids  | 是       | List | id集合 |

返回参数：

| 参数  | 类型   | 说明                                 |
| ----- | ------ | ------------------------------------ |
| msg   | String | 返回信息                             |
| data  | Object | 返回数据                             |
| code  | int    | 状态码(0=成功，500=失败,1000=无权限) |
| count | int    | 返回数据条数                         |

#### 将回收的酒店特色批量恢复到正常状态

接口地址：localhost:8088/hotel/features/recoveryRecycleFeaturesBatch

请求参数：

| 参数 | 是否必填 | 类型 | 说明   |
| ---- | -------- | ---- | ------ |
| ids  | 是       | List | id集合 |

返回参数：

| 参数  | 类型   | 说明                                 |
| ----- | ------ | ------------------------------------ |
| msg   | String | 返回信息                             |
| data  | Object | 返回数据                             |
| code  | int    | 状态码(0=成功，500=失败,1000=无权限) |
| count | int    | 返回数据条数                         |

#### 批量删除酒店特色

接口地址：localhost:8088/hotel/features/deleteFeaturesBatch

请求参数：

| 参数 | 是否必填 | 类型 | 说明   |
| ---- | -------- | ---- | ------ |
| ids  | 是       | List | id集合 |

返回参数：

| 参数  | 类型   | 说明                                 |
| ----- | ------ | ------------------------------------ |
| msg   | String | 返回信息                             |
| data  | Object | 返回数据                             |
| code  | int    | 状态码(0=成功，500=失败,1000=无权限) |
| count | int    | 返回数据条数                         |

## 17.健身器材接口

#### 添加健身器材

接口地址：localhost:8088/hotel/fitness/insertFitness

请求参数：

| 参数             | 是否必填 | 类型          | 说明         |
| ---------------- | -------- | ------------- | ------------ |
| equipmentName    | 是       | String        | 器材名称     |
| equipmentPicture | 是       | MultipartFile | 器材图片     |
| equipmentIntro   | 否       | String        | 特色简介     |
| equipmentNum     | 是       | int           | 器材数量     |
| equipmentUsage   | 否       | String        | 器材使用方法 |

返回参数：

| 参数  | 类型   | 说明                     |
| ----- | ------ | ------------------------ |
| msg   | String | 返回信息                 |
| data  | Object | 返回数据                 |
| code  | int    | 状态码(0=成功，500=失败) |
| count | int    | 返回数据条数             |

#### 修改健身器材

接口地址：localhost:8088/hotel/fitness/updateFitness

请求参数：

| 参数             | 是否必填 | 类型          | 说明                    |
| ---------------- | -------- | ------------- | ----------------------- |
| id               | 是       | int           | 器材id                  |
| equipmentName    | 是       | String        | 器材名称                |
| equipmentPicture | 是       | MultipartFile | 器材图片                |
| equipmentIntro   | 否       | String        | 特色简介                |
| equipmentNum     | 是       | int           | 器材数量                |
| equipmentUsage   | 否       | String        | 器材使用方法            |
| equipmentStates  | 否       | int           | 器材状态(1=正常,2=维修) |

返回参数：

| 参数  | 类型   | 说明                     |
| ----- | ------ | ------------------------ |
| msg   | String | 返回信息                 |
| data  | Object | 返回数据                 |
| code  | int    | 状态码(0=成功，500=失败) |
| count | int    | 返回数据条数             |

#### 根据id回收健身器材

接口地址：localhost:8088/hotel/fitness/recycleFitness

请求参数：

| 参数 | 是否必填 | 类型 | 说明   |
| ---- | -------- | ---- | ------ |
| id   | 是       | int  | 器材id |

返回参数：

| 参数  | 类型   | 说明                     |
| ----- | ------ | ------------------------ |
| msg   | String | 返回信息                 |
| data  | Object | 返回数据                 |
| code  | int    | 状态码(0=成功，500=失败) |
| count | int    | 返回数据条数             |

#### 将回收的健身器材恢复到正常状态

接口地址：localhost:8088/hotel/fitness/recoveryRecycleFitness

请求参数：

| 参数 | 是否必填 | 类型 | 说明   |
| ---- | -------- | ---- | ------ |
| id   | 是       | int  | 器材id |

返回参数：

| 参数  | 类型   | 说明                     |
| ----- | ------ | ------------------------ |
| msg   | String | 返回信息                 |
| data  | Object | 返回数据                 |
| code  | int    | 状态码(0=成功，500=失败) |
| count | int    | 返回数据条数             |

#### 删除健身器材

接口地址：localhost:8088/hotel/fitness/deleteFitness

请求参数：

| 参数 | 是否必填 | 类型 | 说明   |
| ---- | -------- | ---- | ------ |
| id   | 是       | int  | 器材id |

返回参数：

| 参数  | 类型   | 说明                     |
| ----- | ------ | ------------------------ |
| msg   | String | 返回信息                 |
| data  | Object | 返回数据                 |
| code  | int    | 状态码(0=成功，500=失败) |
| count | int    | 返回数据条数             |

#### 根据id查询健身器材

接口地址：localhost:8088/hotel/select/getFitnessById

请求参数：

| 参数 | 是否必填 | 类型 | 说明   |
| ---- | -------- | ---- | ------ |
| id   | 是       | int  | 器材id |

返回参数：

| 参数             | 类型   | 说明                    |
| ---------------- | ------ | ----------------------- |
| equipmentName    | String | 器材名称                |
| equipmentPicture | String | 器材图片                |
| equipmentIntro   | String | 器材简介                |
| equipmentNum     | int    | 器材数量                |
| equipmentUsage   | String | 器材使用方法            |
| createrId        | int    | 创建人id                |
| createrUsername  | String | 创建人用户名            |
| createTime       | date   | 创建时间                |
| modifierId       | int    | 修改人id                |
| modifierUsername | String | 修改人用户名            |
| modifyTime       | date   | 修改时间                |
| equipmentStates  | int    | 器材状态(1=正常,2=维修) |
| existStates      | int    | 存在状态(1=正常,2=删除) |

#### 获取正常状态的健身器材列表

接口地址：localhost:8088/hotel/select/getFitnessList

请求参数：

| 参数  | 是否必填类型 | 类型说明 | 说明     |
| ----- | ------------ | -------- | -------- |
| page  | 是           | int      | 当前页数 |
| limit | 是           | int      | 每页条数 |

返回参数：

| 参数             | 类型   | 说明                    |
| ---------------- | ------ | ----------------------- |
| equipmentName    | String | 器材名称                |
| equipmentPicture | String | 器材图片                |
| equipmentIntro   | String | 器材简介                |
| equipmentNum     | int    | 器材数量                |
| equipmentUsage   | String | 器材使用方法            |
| createrId        | int    | 创建人id                |
| createrUsername  | String | 创建人用户名            |
| createTime       | date   | 创建时间                |
| modifierId       | int    | 修改人id                |
| modifierUsername | String | 修改人用户名            |
| modifyTime       | date   | 修改时间                |
| equipmentStates  | int    | 器材状态(1=正常,2=维修) |
| existStates      | int    | 存在状态(1=正常)        |

#### 获取回收状态的健身器材列表

接口地址：localhost:8088/hotel/select/getRecycleFitnessList

请求参数：

| 参数  | 是否必填类型 | 类型说明 | 说明     |
| ----- | ------------ | -------- | -------- |
| page  | 是           | int      | 当前页数 |
| limit | 是           | int      | 每页条数 |

返回参数：

| 参数             | 类型   | 说明                    |
| ---------------- | ------ | ----------------------- |
| equipmentName    | String | 器材名称                |
| equipmentPicture | String | 器材图片                |
| equipmentIntro   | String | 器材简介                |
| equipmentNum     | int    | 器材数量                |
| equipmentUsage   | String | 器材使用方法            |
| createrId        | int    | 创建人id                |
| createrUsername  | String | 创建人用户名            |
| createTime       | date   | 创建时间                |
| modifierId       | int    | 修改人id                |
| modifierUsername | String | 修改人用户名            |
| modifyTime       | date   | 修改时间                |
| equipmentStates  | int    | 器材状态(1=正常,2=维修) |
| existStates      | int    | 存在状态(2=删除)        |

#### 批量回收健身器材

接口地址：localhost:8088/hotel/fitness/recycleFitnessBatch

请求参数：

| 参数 | 是否必填 | 类型 | 说明   |
| ---- | -------- | ---- | ------ |
| ids  | 是       | List | id集合 |

返回参数：

| 参数  | 类型   | 说明                                 |
| ----- | ------ | ------------------------------------ |
| msg   | String | 返回信息                             |
| data  | Object | 返回数据                             |
| code  | int    | 状态码(0=成功，500=失败,1000=无权限) |
| count | int    | 返回数据条数                         |

#### 将回收的健身器材批量恢复到正常状态

接口地址：localhost:8088/hotel/fitness/recoveryRecycleFitnessBatch

请求参数：

| 参数 | 是否必填 | 类型 | 说明   |
| ---- | -------- | ---- | ------ |
| ids  | 是       | List | id集合 |

返回参数：

| 参数  | 类型   | 说明                                 |
| ----- | ------ | ------------------------------------ |
| msg   | String | 返回信息                             |
| data  | Object | 返回数据                             |
| code  | int    | 状态码(0=成功，500=失败,1000=无权限) |
| count | int    | 返回数据条数                         |

#### 批量删除健身器材

接口地址：localhost:8088/hotel/fitness/deleteFitnessBatch

请求参数：

| 参数 | 是否必填 | 类型 | 说明   |
| ---- | -------- | ---- | ------ |
| ids  | 是       | List | id集合 |

返回参数：

| 参数  | 类型   | 说明                                 |
| ----- | ------ | ------------------------------------ |
| msg   | String | 返回信息                             |
| data  | Object | 返回数据                             |
| code  | int    | 状态码(0=成功，500=失败,1000=无权限) |
| count | int    | 返回数据条数                         |

## 18.酒店历史接口

#### 添加酒店历史

接口地址：localhost:8088/hotel/history/insertHistory

请求参数：

| 参数           | 是否必填 | 类型          | 说明     |
| -------------- | -------- | ------------- | -------- |
| historyIntro   | 否       | String        | 历史简介 |
| historyPicture | 是       | MultipartFile | 历史图片 |

返回参数：

| 参数  | 类型   | 说明                     |
| ----- | ------ | ------------------------ |
| msg   | String | 返回信息                 |
| data  | Object | 返回数据                 |
| code  | int    | 状态码(0=成功，500=失败) |
| count | int    | 返回数据条数             |

#### 修改酒店历史

接口地址：localhost:8088/hotel/history/updateHistory

请求参数：

| 参数           | 是否必填 | 类型          | 说明       |
| -------------- | -------- | ------------- | ---------- |
| id             | 是       | int           | 酒店历史id |
| historyIntro   | 否       | String        | 历史简介   |
| historyPicture | 是       | MultipartFile | 历史图片   |

返回参数：

| 参数  | 类型   | 说明                     |
| ----- | ------ | ------------------------ |
| msg   | String | 返回信息                 |
| data  | Object | 返回数据                 |
| code  | int    | 状态码(0=成功，500=失败) |
| count | int    | 返回数据条数             |

#### 根据id回收酒店历史

接口地址：localhost:8088/hotel/history/recycleHistory

请求参数：

| 参数 | 是否必填 | 类型 | 说明       |
| ---- | -------- | ---- | ---------- |
| id   | 是       | int  | 酒店历史id |

返回参数：

| 参数  | 类型   | 说明                     |
| ----- | ------ | ------------------------ |
| msg   | String | 返回信息                 |
| data  | Object | 返回数据                 |
| code  | int    | 状态码(0=成功，500=失败) |
| count | int    | 返回数据条数             |

#### 将回收的酒店历史恢复到正常状态

接口地址：localhost:8088/hotel/history/recycleRecycleHistory

请求参数：

| 参数 | 是否必填 | 类型 | 说明       |
| ---- | -------- | ---- | ---------- |
| id   | 是       | int  | 酒店历史id |

返回参数：

| 参数  | 类型   | 说明                     |
| ----- | ------ | ------------------------ |
| msg   | String | 返回信息                 |
| data  | Object | 返回数据                 |
| code  | int    | 状态码(0=成功，500=失败) |
| count | int    | 返回数据条数             |

#### 删除酒店历史

接口地址：localhost:8088/hotel/history/deleteHistory

请求参数：

| 参数 | 是否必填 | 类型 | 说明       |
| ---- | -------- | ---- | ---------- |
| id   | 是       | int  | 酒店历史id |

返回参数：

| 参数  | 类型   | 说明                     |
| ----- | ------ | ------------------------ |
| msg   | String | 返回信息                 |
| data  | Object | 返回数据                 |
| code  | int    | 状态码(0=成功，500=失败) |
| count | int    | 返回数据条数             |

#### 根据id查询酒店历史

接口地址：localhost:8088/hotel/select/getHistoryById

请求参数：

| 参数 | 是否必填 | 类型 | 说明       |
| ---- | -------- | ---- | ---------- |
| id   | 是       | int  | 酒店历史id |

返回参数：

| 参数             | 类型   | 说明                    |
| ---------------- | ------ | ----------------------- |
| historyPicture   | String | 历史图片                |
| historyIntro     | String | 历史简介                |
| createrId        | int    | 创建人id                |
| createrUsername  | String | 创建人用户名            |
| createTime       | date   | 创建时间                |
| modifierId       | int    | 修改人id                |
| modifierUsername | String | 修改人用户名            |
| modifyTime       | date   | 修改时间                |
| existStates      | int    | 存在状态(1=正常,2=删除) |

#### 获取正常状态的酒店历史列表

接口地址：localhost:8088/hotel/select/getHistoryList

请求参数：

| 参数  | 是否必填类型 | 类型说明 | 说明     |
| ----- | ------------ | -------- | -------- |
| page  | 是           | int      | 当前页数 |
| limit | 是           | int      | 每页条数 |

返回参数：

| 参数             | 类型   | 说明                    |
| ---------------- | ------ | ----------------------- |
| historyPicture   | String | 历史图片                |
| historyIntro     | String | 历史简介                |
| createrId        | int    | 创建人id                |
| createrUsername  | String | 创建人用户名            |
| createTime       | date   | 创建时间                |
| modifierId       | int    | 修改人id                |
| modifierUsername | String | 修改人用户名            |
| modifyTime       | date   | 修改时间                |
| existStates      | int    | 存在状态(1=正常,2=删除) |

#### 获取回收状态的酒店历史列表

接口地址：localhost:8088/hotel/select/getRecycleHistoryList

请求参数：

| 参数  | 是否必填类型 | 类型说明 | 说明     |
| ----- | ------------ | -------- | -------- |
| page  | 是           | int      | 当前页数 |
| limit | 是           | int      | 每页条数 |

返回参数：

| 参数             | 类型   | 说明                    |
| ---------------- | ------ | ----------------------- |
| historyPicture   | String | 历史图片                |
| historyIntro     | String | 历史简介                |
| createrId        | int    | 创建人id                |
| createrUsername  | String | 创建人用户名            |
| createTime       | date   | 创建时间                |
| modifierId       | int    | 修改人id                |
| modifierUsername | String | 修改人用户名            |
| modifyTime       | date   | 修改时间                |
| existStates      | int    | 存在状态(1=正常,2=删除) |

#### 批量回收酒店历史

接口地址：localhost:8088/hotel/history/recycleHistoryBatch

请求参数：

| 参数 | 是否必填 | 类型 | 说明   |
| ---- | -------- | ---- | ------ |
| ids  | 是       | List | id集合 |

返回参数：

| 参数  | 类型   | 说明                                 |
| ----- | ------ | ------------------------------------ |
| msg   | String | 返回信息                             |
| data  | Object | 返回数据                             |
| code  | int    | 状态码(0=成功，500=失败,1000=无权限) |
| count | int    | 返回数据条数                         |

#### 将回收的酒店历史批量恢复到正常状态

接口地址：localhost:8088/hotel/history/recoveryRecycleHistoryBatch

请求参数：

| 参数 | 是否必填 | 类型 | 说明   |
| ---- | -------- | ---- | ------ |
| ids  | 是       | List | id集合 |

返回参数：

| 参数  | 类型   | 说明                                 |
| ----- | ------ | ------------------------------------ |
| msg   | String | 返回信息                             |
| data  | Object | 返回数据                             |
| code  | int    | 状态码(0=成功，500=失败,1000=无权限) |
| count | int    | 返回数据条数                         |

#### 批量删除酒店历史

接口地址：localhost:8088/hotel/history/deleteHistoryBatch

请求参数：

| 参数 | 是否必填 | 类型 | 说明   |
| ---- | -------- | ---- | ------ |
| ids  | 是       | List | id集合 |

返回参数：

| 参数  | 类型   | 说明                                 |
| ----- | ------ | ------------------------------------ |
| msg   | String | 返回信息                             |
| data  | Object | 返回数据                             |
| code  | int    | 状态码(0=成功，500=失败,1000=无权限) |
| count | int    | 返回数据条数                         |

## 19.通知信息接口

#### 添加通知信息

接口地址：localhost:8088/hotel/message/insertMessage

请求参数：

| 参数    | 是否必填 | 类型   | 说明 |
| ------- | -------- | ------ | ---- |
| theme   | 是       | String | 主题 |
| content | 是       | String | 内容 |

返回参数：

| 参数  | 类型   | 说明                     |
| ----- | ------ | ------------------------ |
| msg   | String | 返回信息                 |
| data  | Object | 返回数据                 |
| code  | int    | 状态码(0=成功，500=失败) |
| count | int    | 返回数据条数             |

#### 修改通知信息

接口地址：localhost:8088/hotel/message/updateMessage

请求参数：

| 参数    | 是否必填 | 类型   | 说明   |
| ------- | -------- | ------ | ------ |
| id      | 是       | int    | 信息id |
| theme   | 否       | String | 主题   |
| content | 否       | String | 内容   |

返回参数：

| 参数  | 类型   | 说明                     |
| ----- | ------ | ------------------------ |
| msg   | String | 返回信息                 |
| data  | Object | 返回数据                 |
| code  | int    | 状态码(0=成功，500=失败) |
| count | int    | 返回数据条数             |

#### 根据id回收通知信息

接口地址：localhost:8088/hotel/message/recycleMessage

请求参数：

| 参数 | 是否必填 | 类型 | 说明   |
| ---- | -------- | ---- | ------ |
| id   | 是       | int  | 信息id |

返回参数：

| 参数  | 类型   | 说明                     |
| ----- | ------ | ------------------------ |
| msg   | String | 返回信息                 |
| data  | Object | 返回数据                 |
| code  | int    | 状态码(0=成功，500=失败) |
| count | int    | 返回数据条数             |

#### 将回收的通知信息恢复到正常状态

接口地址：localhost:8088/hotel/message/recycleRecycleMessage

请求参数：

| 参数 | 是否必填 | 类型 | 说明   |
| ---- | -------- | ---- | ------ |
| id   | 是       | int  | 信息id |

返回参数：

| 参数  | 类型   | 说明                     |
| ----- | ------ | ------------------------ |
| msg   | String | 返回信息                 |
| data  | Object | 返回数据                 |
| code  | int    | 状态码(0=成功，500=失败) |
| count | int    | 返回数据条数             |

#### 删除通知信息

接口地址：localhost:8088/hotel/message/deleteMessage

请求参数：

| 参数 | 是否必填 | 类型 | 说明   |
| ---- | -------- | ---- | ------ |
| id   | 是       | int  | 信息id |

返回参数：

| 参数  | 类型   | 说明                     |
| ----- | ------ | ------------------------ |
| msg   | String | 返回信息                 |
| data  | Object | 返回数据                 |
| code  | int    | 状态码(0=成功，500=失败) |
| count | int    | 返回数据条数             |

#### 根据id查询通知信息

接口地址：localhost:8088/hotel/select/getMessageById

请求参数：

| 参数 | 是否必填 | 类型 | 说明   |
| ---- | -------- | ---- | ------ |
| id   | 是       | int  | 信息id |

返回参数：

| 参数        | 类型   | 说明                    |
| ----------- | ------ | ----------------------- |
| id          | int    | 信息id                  |
| userId      | int    | 用户id                  |
| username    | String | 用户名                  |
| theme       | String | 主题                    |
| content     | String | 内容                    |
| time        | date   | 时间                    |
| existStates | int    | 存在状态(1=正常,2=删除) |

#### 获取正常状态的通知信息列表

接口地址：localhost:8088/hotel/select/getMessageList

请求参数：

| 参数  | 是否必填类型 | 类型说明 | 说明     |
| ----- | ------------ | -------- | -------- |
| page  | 是           | int      | 当前页数 |
| limit | 是           | int      | 每页条数 |

返回参数：

| 参数        | 类型   | 说明             |
| ----------- | ------ | ---------------- |
| id          | int    | 信息id           |
| userId      | int    | 用户id           |
| username    | String | 用户名           |
| theme       | String | 主题             |
| content     | String | 内容             |
| time        | date   | 时间             |
| existStates | int    | 存在状态(1=正常) |

#### 获取回收状态的通知信息列表

接口地址：localhost:8088/hotel/select/getRecycleMessageList

请求参数：

| 参数  | 是否必填类型 | 类型说明 | 说明     |
| ----- | ------------ | -------- | -------- |
| page  | 是           | int      | 当前页数 |
| limit | 是           | int      | 每页条数 |

返回参数：

| 参数        | 类型   | 说明             |
| ----------- | ------ | ---------------- |
| id          | int    | 信息id           |
| userId      | int    | 用户id           |
| username    | String | 用户名           |
| theme       | String | 主题             |
| content     | String | 内容             |
| time        | date   | 时间             |
| existStates | int    | 存在状态(2=删除) |

#### 批量回收通知信息

接口地址：localhost:8088/hotel/message/recycleMessageBatch

请求参数：

| 参数 | 是否必填 | 类型 | 说明   |
| ---- | -------- | ---- | ------ |
| ids  | 是       | List | id集合 |

返回参数：

| 参数  | 类型   | 说明                                 |
| ----- | ------ | ------------------------------------ |
| msg   | String | 返回信息                             |
| data  | Object | 返回数据                             |
| code  | int    | 状态码(0=成功，500=失败,1000=无权限) |
| count | int    | 返回数据条数                         |

#### 将回收的通知信息批量恢复到正常状态

接口地址：localhost:8088/hotel/message/recoveryRecycleMessageBatch

请求参数：

| 参数 | 是否必填 | 类型 | 说明   |
| ---- | -------- | ---- | ------ |
| ids  | 是       | List | id集合 |

返回参数：

| 参数  | 类型   | 说明                                 |
| ----- | ------ | ------------------------------------ |
| msg   | String | 返回信息                             |
| data  | Object | 返回数据                             |
| code  | int    | 状态码(0=成功，500=失败,1000=无权限) |
| count | int    | 返回数据条数                         |

#### 批量删除通知信息

接口地址：localhost:8088/hotel/message/deleteMessageBatch

请求参数：

| 参数 | 是否必填 | 类型 | 说明   |
| ---- | -------- | ---- | ------ |
| ids  | 是       | List | id集合 |

返回参数：

| 参数  | 类型   | 说明                                 |
| ----- | ------ | ------------------------------------ |
| msg   | String | 返回信息                             |
| data  | Object | 返回数据                             |
| code  | int    | 状态码(0=成功，500=失败,1000=无权限) |
| count | int    | 返回数据条数                         |

## 20.意见反馈接口

#### 添加意见反馈

接口地址：localhost:8088/hotel/opinion/insertOpinion

请求参数：

| 参数               | 是否必填 | 类型   | 说明     |
| ------------------ | -------- | ------ | -------- |
| problemType        | 是       | String | 问题类型 |
| problemDescription | 是       | String | 问题描述 |

返回参数：

| 参数  | 类型   | 说明                     |
| ----- | ------ | ------------------------ |
| msg   | String | 返回信息                 |
| data  | Object | 返回数据                 |
| code  | int    | 状态码(0=成功，500=失败) |
| count | int    | 返回数据条数             |

#### 修改意见反馈

接口地址：localhost:8088/hotel/opinion/updateOpinion

请求参数：

| 参数               | 是否必填 | 类型   | 说明     |
| ------------------ | -------- | ------ | -------- |
| id                 | 是       | String | 反馈id   |
| problemType        | 是       | String | 问题类型 |
| problemDescription | 是       | String | 问题描述 |

返回参数：

| 参数  | 类型   | 说明                     |
| ----- | ------ | ------------------------ |
| msg   | String | 返回信息                 |
| data  | Object | 返回数据                 |
| code  | int    | 状态码(0=成功，500=失败) |
| count | int    | 返回数据条数             |

#### 根据id回收意见反馈

接口地址：localhost:8088/hotel/opinion/recycleOpinion

请求参数：

| 参数 | 是否必填 | 类型   | 说明   |
| ---- | -------- | ------ | ------ |
| id   | 是       | String | 反馈id |

返回参数：

| 参数  | 类型   | 说明                     |
| ----- | ------ | ------------------------ |
| msg   | String | 返回信息                 |
| data  | Object | 返回数据                 |
| code  | int    | 状态码(0=成功，500=失败) |
| count | int    | 返回数据条数             |

#### 将回收的意见反馈恢复到正常状态

接口地址：localhost:8088/hotel/opinion/recycleRecycleOpinion

请求参数：

| 参数 | 是否必填 | 类型   | 说明   |
| ---- | -------- | ------ | ------ |
| id   | 是       | String | 反馈id |

返回参数：

| 参数  | 类型   | 说明                     |
| ----- | ------ | ------------------------ |
| msg   | String | 返回信息                 |
| data  | Object | 返回数据                 |
| code  | int    | 状态码(0=成功，500=失败) |
| count | int    | 返回数据条数             |

#### 删除意见反馈

接口地址：localhost:8088/hotel/opinion/deleteOpinion

请求参数：

| 参数 | 是否必填 | 类型   | 说明   |
| ---- | -------- | ------ | ------ |
| id   | 是       | String | 反馈id |

返回参数：

| 参数  | 类型   | 说明                     |
| ----- | ------ | ------------------------ |
| msg   | String | 返回信息                 |
| data  | Object | 返回数据                 |
| code  | int    | 状态码(0=成功，500=失败) |
| count | int    | 返回数据条数             |

#### 根据id查询意见反馈

接口地址：localhost:8088/hotel/select/getOpinionById

请求参数：

| 参数 | 是否必填 | 类型   | 说明   |
| ---- | -------- | ------ | ------ |
| id   | 是       | String | 反馈id |

返回参数：

| 参数               | 类型   | 说明     |
| ------------------ | ------ | -------- |
| id                 | int    | 反馈id   |
| userId             | int    | 用户id   |
| username           | String | 用户名   |
| problemType        | String | 问题类型 |
| problemDescription | String | 问题描述 |
| feedbackTime       | date   | 反馈时间 |
| disposeStates      | int    | 存在状态 |

#### 获取正常状态的意见反馈列表

接口地址：localhost:8088/hotel/select/getOpinionList

请求参数：

| 参数  | 是否必填类型 | 类型说明 | 说明     |
| ----- | ------------ | -------- | -------- |
| page  | 是           | int      | 当前页数 |
| limit | 是           | int      | 每页条数 |

返回参数：

| 参数               | 类型   | 说明     |
| ------------------ | ------ | -------- |
| id                 | int    | 反馈id   |
| userId             | int    | 用户id   |
| username           | String | 用户名   |
| problemType        | String | 问题类型 |
| problemDescription | String | 问题描述 |
| feedbackTime       | date   | 反馈时间 |
| disposeStates      | int    | 存在状态 |

#### 获取回收状态的意见反馈列表

接口地址：localhost:8088/hotel/select/getRecycleOpinionList

请求参数：

| 参数  | 是否必填类型 | 类型说明 | 说明     |
| ----- | ------------ | -------- | -------- |
| page  | 是           | int      | 当前页数 |
| limit | 是           | int      | 每页条数 |

返回参数：

| 参数               | 类型   | 说明     |
| ------------------ | ------ | -------- |
| id                 | int    | 反馈id   |
| userId             | int    | 用户id   |
| username           | String | 用户名   |
| problemType        | String | 问题类型 |
| problemDescription | String | 问题描述 |
| feedbackTime       | date   | 反馈时间 |
| disposeStates      | int    | 存在状态 |

#### 批量回收意见反馈

接口地址：localhost:8088/hotel/opinion/recycleOpinionBatch

请求参数：

| 参数 | 是否必填 | 类型 | 说明   |
| ---- | -------- | ---- | ------ |
| ids  | 是       | List | id集合 |

返回参数：

| 参数  | 类型   | 说明                                 |
| ----- | ------ | ------------------------------------ |
| msg   | String | 返回信息                             |
| data  | Object | 返回数据                             |
| code  | int    | 状态码(0=成功，500=失败,1000=无权限) |
| count | int    | 返回数据条数                         |

#### 将回收的意见反馈批量恢复到正常状态

接口地址：localhost:8088/hotel/opinion/recoveryRecycleOpinionBatch

请求参数：

| 参数 | 是否必填 | 类型 | 说明   |
| ---- | -------- | ---- | ------ |
| ids  | 是       | List | id集合 |

返回参数：

| 参数  | 类型   | 说明                                 |
| ----- | ------ | ------------------------------------ |
| msg   | String | 返回信息                             |
| data  | Object | 返回数据                             |
| code  | int    | 状态码(0=成功，500=失败,1000=无权限) |
| count | int    | 返回数据条数                         |

#### 批量删除意见反馈

接口地址：localhost:8088/hotel/opinion/deleteOpinionBatch

请求参数：

| 参数 | 是否必填 | 类型 | 说明   |
| ---- | -------- | ---- | ------ |
| ids  | 是       | List | id集合 |

返回参数：

| 参数  | 类型   | 说明                                 |
| ----- | ------ | ------------------------------------ |
| msg   | String | 返回信息                             |
| data  | Object | 返回数据                             |
| code  | int    | 状态码(0=成功，500=失败,1000=无权限) |
| count | int    | 返回数据条数                         |

## 21.用户餐厅接口

#### 用户下单

接口地址：localhost:8088/hotel/userRestaurant/insertUserRestaurant

请求参数：

| 参数         | 是否必填 | 类型 | 说明       |
| ------------ | -------- | ---- | ---------- |
| userId       | 是       | int  | 用户id     |
| restaurantId | 是       | int  | 餐厅物品id |
| foodNum      | 是       | int  | 食品数量   |

返回参数：

| 参数  | 类型   | 说明                     |
| ----- | ------ | ------------------------ |
| msg   | String | 返回信息                 |
| data  | Object | 返回数据                 |
| code  | int    | 状态码(0=成功，500=失败) |
| count | int    | 返回数据条数             |

#### 修改订单

接口地址：localhost:8088/hotel/userRestaurant/updateUserRestaurant

请求参数：

| 参数         | 是否必填 | 类型 | 说明       |
| ------------ | -------- | ---- | ---------- |
| userId       | 是       | int  | 用户id     |
| restaurantId | 是       | int  | 餐厅物品id |
| foodNum      | 是       | int  | 食品数量   |

返回参数：

| 参数  | 类型   | 说明                     |
| ----- | ------ | ------------------------ |
| msg   | String | 返回信息                 |
| data  | Object | 返回数据                 |
| code  | int    | 状态码(0=成功，500=失败) |
| count | int    | 返回数据条数             |

#### 交易完成（送达）

接口地址：localhost:8088/hotel/userRestaurant/cancel

请求参数：

| 参数              | 是否必填 | 类型   | 说明       |
| ----------------- | -------- | ------ | ---------- |
| id                | 是       | int    | 用户餐厅id |
| consumptionType   | 是       | String | 消费类型   |
| tradingManner     | 是       | String | 交易方式   |
| transactionAmount | 是       | String | 交易金额   |

返回参数：

| 参数  | 类型   | 说明                     |
| ----- | ------ | ------------------------ |
| msg   | String | 返回信息                 |
| data  | Object | 返回数据                 |
| code  | int    | 状态码(0=成功，500=失败) |
| count | int    | 返回数据条数             |

#### 取消订单

接口地址：localhost:8088/hotel/userRestaurant/deleteUserRestaurant

请求参数：

| 参数         | 是否必填 | 类型 | 说明       |
| ------------ | -------- | ---- | ---------- |
| id           | 是       | int  | 用户餐厅id |
| restaurantId | 是       | int  | 餐厅物品id |
| foodNum      | 是       | int  | 食品数量   |

返回参数：

| 参数  | 类型   | 说明                     |
| ----- | ------ | ------------------------ |
| msg   | String | 返回信息                 |
| data  | Object | 返回数据                 |
| code  | int    | 状态码(0=成功，500=失败) |
| count | int    | 返回数据条数             |

#### 根据id查询订单

接口地址：localhost:8088/hotel/select/getUserRestaurantById

请求参数：

| 参数 | 是否必填 | 类型 | 说明       |
| ---- | -------- | ---- | ---------- |
| id   | 是       | int  | 用户餐厅id |

返回参数：

| 参数               | 类型   | 说明                        |
| ------------------ | ------ | --------------------------- |
| id                 | int    | 用户餐厅id                  |
| userId             | int    | 用户id                      |
| restaurantId       | int    | 餐厅物品id                  |
| username           | String | 用户名                      |
| foodName           | String | 食品名称                    |
| foodUnitPrice      | String | 食品单价                    |
| foodNum            | int    | 食品数量                    |
| orderTime          | date   | 下单时间                    |
| orderOverdueStates | int    | 订单是否过期(1=正常,2=过期) |
| orderStates        | int    | 订单状态(1=已支付,2=未支付) |

#### 获取用户商品订单分页列表

接口地址：localhost:8088/hotel/select/getUserRestaurantList

请求参数：

| 参数  | 是否必填类型 | 类型说明 | 说明     |
| ----- | ------------ | -------- | -------- |
| page  | 是           | int      | 当前页数 |
| limit | 是           | int      | 每页条数 |

返回参数：

| 参数               | 类型   | 说明                        |
| ------------------ | ------ | --------------------------- |
| id                 | int    | 用户餐厅id                  |
| userId             | int    | 用户id                      |
| restaurantId       | int    | 餐厅物品id                  |
| username           | String | 用户名                      |
| foodName           | String | 食品名称                    |
| foodUnitPrice      | String | 食品单价                    |
| foodNum            | int    | 食品数量                    |
| orderTime          | date   | 下单时间                    |
| orderOverdueStates | int    | 订单是否过期(1=正常,2=过期) |
| orderStates        | int    | 订单状态(1=已支付,2=未支付) |

#### 搜索订单（模糊查询）

接口地址：localhost:8088/hotel/select/searchUserRestaurant

请求参数：

| 参数        | 是否必填                       | 类型   | 说明     |
| ----------- | ------------------------------ | ------ | -------- |
| orderNumber | 至少有一项必填，也可以两项都填 | String | 订单编号 |
| username    | 至少有一项必填，也可以两项都填 | String | 用户名   |

返回参数：

| 参数               | 类型   | 说明                        |
| ------------------ | ------ | --------------------------- |
| id                 | int    | 用户商品id                  |
| userId             | int    | 用户id                      |
| restaurantId       | int    | 餐厅物品id                  |
| username           | String | 用户名                      |
| orderNumber        | String | 订单编号                    |
| foodName           | String | 食品名称                    |
| foodUnitPrice      | String | 食品单价                    |
| foodNum            | int    | 食品数量                    |
| orderTime          | Date   | 下单时间                    |
| orderOverdueStates | int    | 订单是否过期(1=正常,2=过期) |
| orderStates        | int    | 支付状态(1=已支付,2=未支付) |

| 参数  | 类型   | 说明                                 |
| ----- | ------ | ------------------------------------ |
| msg   | String | 返回信息                             |
| data  | Object | 返回数据                             |
| code  | int    | 状态码(0=成功，500=失败,1000=无权限) |
| count | int    | 返回数据条数                         |

#### 查询已付款的订单

接口地址：localhost:8088/hotel/select/getPaidUserRestaurant

请求参数：

| 参数  | 是否必填 | 类型 | 说明     |
| ----- | -------- | ---- | -------- |
| page  | 是       | int  | 当前页数 |
| limit | 是       | int  | 每页条数 |

返回参数：

| 参数               | 类型   | 说明                        |
| ------------------ | ------ | --------------------------- |
| id                 | int    | 用户商品id                  |
| userId             | int    | 用户id                      |
| restaurantId       | int    | 餐厅物品id                  |
| username           | String | 用户名                      |
| orderNumber        | String | 订单编号                    |
| foodName           | String | 食品名称                    |
| foodUnitPrice      | String | 食品单价                    |
| foodNum            | int    | 食品数量                    |
| orderTime          | Date   | 下单时间                    |
| orderOverdueStates | int    | 订单是否过期(1=正常,2=过期) |
| orderStates        | int    | 支付状态(1=已支付,2=未支付) |

| 参数  | 类型   | 说明                                 |
| ----- | ------ | ------------------------------------ |
| msg   | String | 返回信息                             |
| data  | Object | 返回数据                             |
| code  | int    | 状态码(0=成功，500=失败,1000=无权限) |
| count | int    | 返回数据条数                         |

#### 查询未付款的订单

接口地址：localhost:8088/hotel/select/getUnPaidUserRestaurant

请求参数：

| 参数  | 是否必填 | 类型 | 说明     |
| ----- | -------- | ---- | -------- |
| page  | 是       | int  | 当前页数 |
| limit | 是       | int  | 每页条数 |

返回参数：

| 参数               | 类型   | 说明                        |
| ------------------ | ------ | --------------------------- |
| id                 | int    | 用户商品id                  |
| userId             | int    | 用户id                      |
| restaurantId       | int    | 餐厅物品id                  |
| username           | String | 用户名                      |
| orderNumber        | String | 订单编号                    |
| foodName           | String | 食品名称                    |
| foodUnitPrice      | String | 食品单价                    |
| foodNum            | int    | 食品数量                    |
| orderTime          | Date   | 下单时间                    |
| orderOverdueStates | int    | 订单是否过期(1=正常,2=过期) |
| orderStates        | int    | 支付状态(1=已支付,2=未支付) |

| 参数  | 类型   | 说明                                 |
| ----- | ------ | ------------------------------------ |
| msg   | String | 返回信息                             |
| data  | Object | 返回数据                             |
| code  | int    | 状态码(0=成功，500=失败,1000=无权限) |
| count | int    | 返回数据条数                         |

#### 根据下单时间降序排序

接口地址：localhost:8088/hotel/select/getUserRestaurantListByOrderTimeSortDesc

请求参数：

| 参数  | 是否必填 | 类型 | 说明     |
| ----- | -------- | ---- | -------- |
| page  | 是       | int  | 当前页数 |
| limit | 是       | int  | 每页条数 |

返回参数：

| 参数               | 类型   | 说明                        |
| ------------------ | ------ | --------------------------- |
| id                 | int    | 用户商品id                  |
| userId             | int    | 用户id                      |
| restaurantId       | int    | 餐厅物品id                  |
| username           | String | 用户名                      |
| orderNumber        | String | 订单编号                    |
| foodName           | String | 食品名称                    |
| foodUnitPrice      | String | 食品单价                    |
| foodNum            | int    | 食品数量                    |
| orderTime          | Date   | 下单时间                    |
| orderOverdueStates | int    | 订单是否过期(1=正常,2=过期) |
| orderStates        | int    | 支付状态(1=已支付,2=未支付) |

| 参数  | 类型   | 说明                                 |
| ----- | ------ | ------------------------------------ |
| msg   | String | 返回信息                             |
| data  | Object | 返回数据                             |
| code  | int    | 状态码(0=成功，500=失败,1000=无权限) |
| count | int    | 返回数据条数                         |

#### 根据下单时间升序排序

接口地址：localhost:8088/hotel/select/getUserRestaurantListByOrderTimeSortAsc

请求参数：

| 参数  | 是否必填 | 类型 | 说明     |
| ----- | -------- | ---- | -------- |
| page  | 是       | int  | 当前页数 |
| limit | 是       | int  | 每页条数 |

返回参数：

| 参数               | 类型   | 说明                        |
| ------------------ | ------ | --------------------------- |
| id                 | int    | 用户商品id                  |
| userId             | int    | 用户id                      |
| restaurantId       | int    | 餐厅物品id                  |
| username           | String | 用户名                      |
| orderNumber        | String | 订单编号                    |
| foodName           | String | 食品名称                    |
| foodUnitPrice      | String | 食品单价                    |
| foodNum            | int    | 食品数量                    |
| orderTime          | Date   | 下单时间                    |
| orderOverdueStates | int    | 订单是否过期(1=正常,2=过期) |
| orderStates        | int    | 支付状态(1=已支付,2=未支付) |

| 参数  | 类型   | 说明                                 |
| ----- | ------ | ------------------------------------ |
| msg   | String | 返回信息                             |
| data  | Object | 返回数据                             |
| code  | int    | 状态码(0=成功，500=失败,1000=无权限) |
| count | int    | 返回数据条数                         |

#### 查询某个用户的所有订单

接口地址：localhost:8088/hotel/select/getUserRestaurantListByUserId

请求参数：

| 参数   | 是否必填 | 类型 | 说明   |
| ------ | -------- | ---- | ------ |
| userId | 是       | int  | 用户id |

返回参数：

| 参数               | 类型   | 说明                        |
| ------------------ | ------ | --------------------------- |
| id                 | int    | 用户商品id                  |
| userId             | int    | 用户id                      |
| restaurantId       | int    | 餐厅物品id                  |
| username           | String | 用户名                      |
| orderNumber        | String | 订单编号                    |
| foodName           | String | 食品名称                    |
| foodUnitPrice      | String | 食品单价                    |
| foodNum            | int    | 食品数量                    |
| orderTime          | Date   | 下单时间                    |
| orderOverdueStates | int    | 订单是否过期(1=正常,2=过期) |
| orderStates        | int    | 支付状态(1=已支付,2=未支付) |

| 参数  | 类型   | 说明                                 |
| ----- | ------ | ------------------------------------ |
| msg   | String | 返回信息                             |
| data  | Object | 返回数据                             |
| code  | int    | 状态码(0=成功，500=失败,1000=无权限) |
| count | int    | 返回数据条数                         |

#### 查询某个用户任意时间段的订单

接口地址：localhost:8088/hotel/select/getUserRestaurantListByUserIdAndTime

请求参数：

| 参数   | 是否必填 | 类型 | 说明               |
| ------ | -------- | ---- | ------------------ |
| userId | 是       | int  | 用户id             |
| start  | 是       | Date | 开始时间(精确到天) |
| end    | 是       | Date | 结束时间(精确到天) |

返回参数：

| 参数               | 类型   | 说明                        |
| ------------------ | ------ | --------------------------- |
| id                 | int    | 用户商品id                  |
| userId             | int    | 用户id                      |
| restaurantId       | int    | 餐厅物品id                  |
| username           | String | 用户名                      |
| orderNumber        | String | 订单编号                    |
| foodName           | String | 食品名称                    |
| foodUnitPrice      | String | 食品单价                    |
| foodNum            | int    | 食品数量                    |
| orderTime          | Date   | 下单时间                    |
| orderOverdueStates | int    | 订单是否过期(1=正常,2=过期) |
| orderStates        | int    | 支付状态(1=已支付,2=未支付) |

| 参数  | 类型   | 说明                                 |
| ----- | ------ | ------------------------------------ |
| msg   | String | 返回信息                             |
| data  | Object | 返回数据                             |
| code  | int    | 状态码(0=成功，500=失败,1000=无权限) |
| count | int    | 返回数据条数                         |

#### 查询任意时间段的所有订单

接口地址：localhost:8088/hotel/select/getUserRestaurantListByTime

请求参数：

| 参数  | 是否必填 | 类型 | 说明               |
| ----- | -------- | ---- | ------------------ |
| start | 是       | Date | 开始时间(精确到天) |
| end   | 是       | Date | 结束时间(精确到天) |

返回参数：

| 参数               | 类型   | 说明                        |
| ------------------ | ------ | --------------------------- |
| id                 | int    | 用户商品id                  |
| userId             | int    | 用户id                      |
| restaurantId       | int    | 餐厅物品id                  |
| username           | String | 用户名                      |
| orderNumber        | String | 订单编号                    |
| foodName           | String | 食品名称                    |
| foodUnitPrice      | String | 食品单价                    |
| foodNum            | int    | 食品数量                    |
| orderTime          | Date   | 下单时间                    |
| orderOverdueStates | int    | 订单是否过期(1=正常,2=过期) |
| orderStates        | int    | 支付状态(1=已支付,2=未支付) |

| 参数  | 类型   | 说明                                 |
| ----- | ------ | ------------------------------------ |
| msg   | String | 返回信息                             |
| data  | Object | 返回数据                             |
| code  | int    | 状态码(0=成功，500=失败,1000=无权限) |
| count | int    | 返回数据条数                         |

#### 统计某个用户任意时间段的消费（以天分组）

接口地址：localhost:8088/hotel/select/getUserRestaurantPriceByUserIdDay

请求参数：

| 参数   | 是否必填 | 类型 | 说明               |
| ------ | -------- | ---- | ------------------ |
| userId | 是       | int  | 用户id             |
| start  | 是       | Date | 开始时间(精确到天) |
| end    | 是       | Date | 结束时间(精确到天) |

返回参数：

| 参数       | 类型 | 说明     |
| ---------- | ---- | -------- |
| price      | int  | 总金额   |
| order_time | Date | 精确到天 |

| 参数  | 类型   | 说明                     |
| ----- | ------ | ------------------------ |
| msg   | String | 返回信息                 |
| data  | Object | 返回数据                 |
| code  | int    | 状态码(0=成功，500=失败) |
| count | int    | 返回数据条数             |

#### 统计某个用户任意时间段的消费（以月分组）

接口地址：localhost:8088/hotel/select/getUserRestaurantPriceByUserIdMonth

请求参数：

| 参数   | 是否必填 | 类型 | 说明               |
| ------ | -------- | ---- | ------------------ |
| userId | 是       | int  | 用户id             |
| start  | 是       | Date | 开始时间(精确到天) |
| end    | 是       | Date | 结束时间(精确到天) |

返回参数：

| 参数       | 类型 | 说明     |
| ---------- | ---- | -------- |
| price      | int  | 总金额   |
| order_time | Date | 精确到月 |

| 参数  | 类型   | 说明                     |
| ----- | ------ | ------------------------ |
| msg   | String | 返回信息                 |
| data  | Object | 返回数据                 |
| code  | int    | 状态码(0=成功，500=失败) |
| count | int    | 返回数据条数             |

#### 统计某个用户任意时间段的消费（以年分组）

接口地址：localhost:8088/hotel/select/getUserRestaurantPriceByUserIdYear

请求参数：

| 参数   | 是否必填 | 类型 | 说明               |
| ------ | -------- | ---- | ------------------ |
| userId | 是       | int  | 用户id             |
| start  | 是       | Date | 开始时间(精确到天) |
| end    | 是       | Date | 结束时间(精确到天) |

返回参数：

| 参数       | 类型 | 说明     |
| ---------- | ---- | -------- |
| price      | int  | 总金额   |
| order_time | Date | 精确到年 |

| 参数  | 类型   | 说明                     |
| ----- | ------ | ------------------------ |
| msg   | String | 返回信息                 |
| data  | Object | 返回数据                 |
| code  | int    | 状态码(0=成功，500=失败) |
| count | int    | 返回数据条数             |

#### 统计任意时间段的总数量（以天分组）

接口地址：localhost:8088/hotel/select/getUserRestaurantCountsByDay

请求参数：

| 参数  | 是否必填 | 类型 | 说明               |
| ----- | -------- | ---- | ------------------ |
| start | 是       | Date | 开始时间(精确到天) |
| end   | 是       | Date | 结束时间(精确到天) |

返回参数：

| 参数       | 类型 | 说明     |
| ---------- | ---- | -------- |
| counts     | int  | 总数量   |
| order_time | Date | 精确到天 |

| 参数  | 类型   | 说明                     |
| ----- | ------ | ------------------------ |
| msg   | String | 返回信息                 |
| data  | Object | 返回数据                 |
| code  | int    | 状态码(0=成功，500=失败) |
| count | int    | 返回数据条数             |

#### 统计任意时间段的总金额（以天分组）

接口地址：localhost:8088/hotel/select/getUserRestaurantPriceByDay

请求参数：

| 参数  | 是否必填 | 类型 | 说明               |
| ----- | -------- | ---- | ------------------ |
| start | 是       | Date | 开始时间(精确到天) |
| end   | 是       | Date | 结束时间(精确到天) |

返回参数：

| 参数       | 类型 | 说明     |
| ---------- | ---- | -------- |
| price      | int  | 总金额   |
| order_time | Date | 精确到天 |

| 参数  | 类型   | 说明                     |
| ----- | ------ | ------------------------ |
| msg   | String | 返回信息                 |
| data  | Object | 返回数据                 |
| code  | int    | 状态码(0=成功，500=失败) |
| count | int    | 返回数据条数             |

#### 统计任意时间段的总数量（以月分组）

接口地址：localhost:8088/hotel/select/getUserRestaurantCountsByMonth

请求参数：

| 参数  | 是否必填 | 类型 | 说明               |
| ----- | -------- | ---- | ------------------ |
| start | 是       | Date | 开始时间(精确到天) |
| end   | 是       | Date | 结束时间(精确到天) |

返回参数：

| 参数       | 类型 | 说明     |
| ---------- | ---- | -------- |
| counts     | int  | 总数量   |
| order_time | Date | 精确到月 |

| 参数  | 类型   | 说明                     |
| ----- | ------ | ------------------------ |
| msg   | String | 返回信息                 |
| data  | Object | 返回数据                 |
| code  | int    | 状态码(0=成功，500=失败) |
| count | int    | 返回数据条数             |

#### 统计任意时间段的总金额（以月分组）

接口地址：localhost:8088/hotel/select/getUserRestaurantPriceByMonth

请求参数：

| 参数  | 是否必填 | 类型 | 说明               |
| ----- | -------- | ---- | ------------------ |
| start | 是       | Date | 开始时间(精确到天) |
| end   | 是       | Date | 结束时间(精确到天) |

返回参数：

| 参数       | 类型 | 说明     |
| ---------- | ---- | -------- |
| price      | int  | 总金额   |
| order_time | Date | 精确到月 |

| 参数  | 类型   | 说明                     |
| ----- | ------ | ------------------------ |
| msg   | String | 返回信息                 |
| data  | Object | 返回数据                 |
| code  | int    | 状态码(0=成功，500=失败) |
| count | int    | 返回数据条数             |

#### 统计任意时间段的总数量（以年分组）

接口地址：localhost:8088/hotel/select/getUserRestaurantCountsByYear

请求参数：

| 参数  | 是否必填 | 类型 | 说明               |
| ----- | -------- | ---- | ------------------ |
| start | 是       | Date | 开始时间(精确到天) |
| end   | 是       | Date | 结束时间(精确到天) |

返回参数：

| 参数       | 类型 | 说明     |
| ---------- | ---- | -------- |
| counts     | int  | 总数量   |
| order_time | Date | 精确到年 |

| 参数  | 类型   | 说明                     |
| ----- | ------ | ------------------------ |
| msg   | String | 返回信息                 |
| data  | Object | 返回数据                 |
| code  | int    | 状态码(0=成功，500=失败) |
| count | int    | 返回数据条数             |

#### 统计任意时间段的总金额（以年分组）

接口地址：localhost:8088/hotel/select/getUserRestaurantPriceByYear

请求参数：

| 参数  | 是否必填 | 类型 | 说明               |
| ----- | -------- | ---- | ------------------ |
| start | 是       | Date | 开始时间(精确到天) |
| end   | 是       | Date | 结束时间(精确到天) |

返回参数：

| 参数       | 类型 | 说明     |
| ---------- | ---- | -------- |
| price      | int  | 总金额   |
| order_time | Date | 精确到年 |

| 参数  | 类型   | 说明                     |
| ----- | ------ | ------------------------ |
| msg   | String | 返回信息                 |
| data  | Object | 返回数据                 |
| code  | int    | 状态码(0=成功，500=失败) |
| count | int    | 返回数据条数             |

#### 批量删除用户商品订单

接口地址：localhost:8088/hotel/userProduct/deleteUserProductBatch

请求参数：

| 参数 | 是否必填 | 类型 | 说明   |
| ---- | -------- | ---- | ------ |
| ids  | 是       | List | id集合 |

返回参数：

| 参数  | 类型   | 说明                                 |
| ----- | ------ | ------------------------------------ |
| msg   | String | 返回信息                             |
| data  | Object | 返回数据                             |
| code  | int    | 状态码(0=成功，500=失败,1000=无权限) |
| count | int    | 返回数据条数                         |



## 22.人脸识别接口

#### 添加人脸信息

接口地址：localhost:8088/hotel/face/faceAdd

请求参数：

| 参数     | 是否必填 | 类型          | 说明     |
| -------- | -------- | ------------- | -------- |
| faceFile | 是       | multipartFile | 人脸图片 |
| name     | 是       | String        | 名字     |
| groupId  | 是       | String        | 分组id   |

返回参数：

| 参数  | 类型   | 说明                     |
| ----- | ------ | ------------------------ |
| msg   | String | 返回信息                 |
| data  | Object | 返回数据                 |
| code  | int    | 状态码(0=成功，500=失败) |
| count | int    | 返回数据条数             |

#### 人脸比对

接口地址：localhost:8088/hotel/face/faceSearch

请求参数：

| 参数     | 是否必填 | 类型          | 说明     |
| -------- | -------- | ------------- | -------- |
| faceFile | 是       | multipartFile | 人脸图片 |
| name     | 是       | String        | 名字     |
| groupId  | 是       | String        | 分组id   |

返回参数：

#### 检测人脸

接口地址：localhost:8088/hotel/face/detectFaces

请求参数：

| 参数     | 是否必填 | 类型          | 说明     |
| -------- | -------- | ------------- | -------- |
| faceFile | 是       | multipartFile | 人脸图片 |

返回参数：

#### 删除人脸

接口地址：localhost:8088/hotel/face/deleteFace

请求参数：

返回参数：

#### 根据id获取人脸信息

接口地址：localhost:8088/hotel/select/getUserFaceInfoById

请求参数：

返回参数：

#### 获取人脸信息列表

接口地址：localhost:8088/hotel/select/getUserFaceInfoList

请求参数：

返回参数：
