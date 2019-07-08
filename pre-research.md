# 中国互联网公司市值实时排名(inc)

## 数据来源

### 股票数据

#### 上市公司

[聚合数据](http://www.juhe.cn)

[股票数据API接口详情](https://www.juhe.cn/docs/api/id/21)

示例：

**沪深股市**

```json
GET http://web.juhe.cn:8080/finance/stock/hs?gid={code}&key={appkey}&type=0
```

0-上证，1-深证

**香港股市**

```json
GET http://web.juhe.cn:8080/finance/stock/hk?num={code}&key={appkey}
```

**美国股市**

```json
 GET http://web.juhe.cn:8080/finance/stock/usa?gid={code}&key={appkey}
```

#### 未上市公司(暂不列入排名)



### 汇率数据

[聚合数据](http://www.juhe.cn)

[汇率数据API接口详情](https://www.juhe.cn/docs/api/id/80)



## 技术选型

## 前端

Vue



## 后端

Spring Boot

