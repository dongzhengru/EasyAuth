<p align="center">
	<img alt="logo" src="https://easyauth.zhengru.top/easyauth-logo.png" width="150" height="150">
</p>
<h1 align="center" style="margin: 30px 0 30px; font-weight: bold;">EasyAuth v0.0.1 Dev</h1>
<h4 align="center">基于Oauth2.0的统一身份认证平台（开发中）</h4>
<p align="center">
	<a href="https://github.com/dongzhengru/EasyAuth"><img src="https://img.shields.io/badge/Spring%20Boot-2.5.13-brightgreen"></a>
	<a href="https://github.com/dongzhengru/EasyAuth"><img src="https://img.shields.io/badge/Version-0.0.1--Beta-blue"></a>
	<a href="https://github.com/dongzhengru/EasyAuth"><img src="https://img.shields.io/badge/license-MIT-green"></a>
	<a href="https://github.com/dongzhengru/EasyAuth"><img src="https://img.shields.io/badge/CI-enabled-brightgreen"></a>
</p>
<p align="center"><a href="https://easyauth.zhengru.top" target="_blank">在线文档：https://easyauth.zhengru.top</a></p>


## 简述
EasyAuth 是基于 Oauth2.0 授权流程和 SpringBoot 技术实现的一个轻量级、高可用、低侵入的统一身份认证平台。

## 项目结构

```lua
easy-auth
├── easy-auth-demo -- Demo应用客户端
├── easy-auth-server -- 认证中心服务端
├── easy-auth-starter -- 依赖装配模块
│   ├── easy-auth-starter-base -- 公用基础常量工具模块
│   ├── easy-auth-starter-client -- 客户端依赖模块
│   ├── easy-auth-starter-server -- 服务端依赖模块
├── easy-auth-unified-portal -- 统一门户（开放平台）服务端
```
