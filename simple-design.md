

## 实体字段类型说明

| 字段名 | 数据类型 | 说明    |
| :----- | -------- | ------- |
| name   | 名称     | String  |
| code   | 代码     | String  |
| source | 来源     | String  |
| money  | 市值     | Double  |
| rank   | 排名     | Integer |
| update | 时间     | Long    |

## RESTful API接口

### 1. 查询公司排名列表

#### 功能介绍

- 返回公司市值排名

#### URI

```GET /rank```

#### 示例

- 请求示例

```GET /rank```

- 响应示例

成功

```json
{
    "message":"成功",
    "code":0,
    "data":[
        "name":"公司名称",
        "code":"上市代码",
        "source":"上证综指/香港股市/美国股市",
        "money":"市值(美元)",
        "rank":"排名",
	      "update":"排名更新时间"
    ]
}
```

失败

```json
{    
    "message":"失败原因",
    "code":-1,
    "data":[]
}
```

