# Uniyex Coder Services
## 序曲：
* 元次元（重庆）科技有限公司
* 初创公司，可以提供专业、全面且具有工匠精神的学习经验及教学。
* 如果你是初学者、想成为一名编程者、想提高编程能力、想学习架构，又或者是实力超群，均可一起交流。
* 我相信："
  - **努力前行定不负韶华，生命有限，但房角石（代码）永存；**
  - **持续学习绝没有边界，无论你我，这条路必然会永无止尽。**"
  
## 服务介绍
### 示例
1. [x] 魔术猜谜


## 架构指导手册 -- Pending
### 接口设计
- 根据领域模型划分微服务（Microservice）或者单体服务（Standalone）的package
- 遵循RESTFul API最佳实践设计接口
- API接口可根据应用场景大体分为Admin管理端和各种前端（包括但不限于H5、小程序、Android、iOS）
    - 资源层数，建议不超过`5`层
    - 多个单词，建议以横线`-`连接
    - 顶层资源根据各端确定，比如:
        - Admin管理后台以`/admin`作为顶层资源，用户接口：`/admin/users`
        - 前端以`/api`作为顶层资源，用户接口，：`/api/users `
    - 通用服务/资源必须高度抽象，比如:
        - IAM授权系统用户：`/iam/sys-users`
        - IAM授权C端用户：`/iam/c-users`
        - IAM授权B端用户：`/iam/b-users`
        - 文章列表（各端通用）：`/news/page`,去掉各端的顶层资源


# 联系我
* 邮箱: uniyex@hotmail.com
* 企业微信:  
![UniyexCoder](/example/doc/uniyex-coder-wecom-qr.png "UniyexCoder")

# 欢迎加入交流！
