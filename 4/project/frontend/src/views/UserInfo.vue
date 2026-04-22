<template>
  <div class="user-page">
    <!-- 头部导航 -->
    <header class="header">
      <div class="logo">
        <span class="logo-icon">📊</span>
        <span class="logo-text">SSM + Vue 前后端整合项目</span>
      </div>
      <nav class="nav">
        <router-link to="/" class="nav-link">首页</router-link>
        <router-link to="/user" class="nav-link active">用户管理</router-link>
      </nav>
    </header>

    <!-- 主体内容 -->
    <main class="main">
      <div class="container">
        <!-- 用户信息卡片 -->
        <div class="card info-card">
          <div class="card-header">
            <span class="card-icon">👤</span>
            <h2 class="card-title">用户信息</h2>
          </div>
          <div class="card-body">
            <div v-if="loading" class="loading">
              <div class="spinner"></div>
              <p>加载中...</p>
            </div>
            <div v-else-if="userInfo" class="info-content">
              <div class="info-row">
                <span class="info-label">姓名：</span>
                <span class="info-value">{{ userInfo.name }}</span>
              </div>
              <div class="info-row">
                <span class="info-label">年龄：</span>
                <span class="info-value">{{ userInfo.age }} 岁</span>
              </div>
              <div class="info-row">
                <span class="info-label">邮箱：</span>
                <span class="info-value">{{ userInfo.email }}</span>
              </div>
            </div>
            <div v-else class="empty-state">
              <p>暂无数据，点击下方按钮获取</p>
            </div>
            <button @click="fetchUserInfo" class="btn btn-primary" :disabled="loading">
              🔄 获取用户信息
            </button>
          </div>
        </div>

        <!-- 用户列表卡片 -->
        <div class="card list-card">
          <div class="card-header">
            <span class="card-icon">📋</span>
            <h2 class="card-title">用户列表</h2>
            <button @click="fetchUserList" class="btn-refresh" title="刷新列表">
              🔄
            </button>
          </div>
          <div class="card-body">
            <div v-if="listLoading" class="loading">
              <div class="spinner"></div>
              <p>加载中...</p>
            </div>
            <div v-else-if="userList.length > 0" class="table-wrapper">
              <table class="user-table">
                <thead>
                  <tr>
                    <th>ID</th>
                    <th>用户名</th>
                    <th>邮箱</th>
                  </tr>
                </thead>
                <tbody>
                  <tr v-for="user in userList" :key="user.id">
                    <td>{{ user.id }}</td>
                    <td>{{ user.username }}</td>
                    <td>{{ user.email }}</td>
                  </tr>
                </tbody>
              </table>
            </div>
            <div v-else class="empty-state">
              <p>暂无用户数据，请确保数据库中有数据</p>
            </div>
            <button @click="fetchUserList" class="btn btn-secondary" :disabled="listLoading">
              📋 获取用户列表
            </button>
          </div>
        </div>
      </div>
    </main>

    <!-- 底部 -->
    <footer class="footer">
      <p>SSM + Vue 前后端分离实验项目 | 后端接口：http://localhost:8080/ssm-backend-1.0-SNAPSHOT</p>
    </footer>
  </div>
</template>

<script>
import axios from 'axios'

const BACKEND_URL = 'http://localhost:8080/ssm-backend-1.0-SNAPSHOT'

export default {
  name: 'UserInfo',
  data() {
    return {
      userInfo: null,
      userList: [],
      loading: false,
      listLoading: false
    }
  },
  mounted() {
    // 自动加载数据
    this.fetchUserInfo()
    this.fetchUserList()
  },
  methods: {
    async fetchUserInfo() {
      this.loading = true
      try {
        const res = await axios.get(BACKEND_URL + '/api/user/info')
        if (res.data.code === 200) {
          this.userInfo = res.data.data
        }
      } catch (error) {
        console.error('获取用户信息失败:', error)
        alert('获取用户信息失败，请检查后端服务是否启动')
      } finally {
        this.loading = false
      }
    },
    async fetchUserList() {
      this.listLoading = true
      try {
        const res = await axios.get(BACKEND_URL + '/api/user/list')
        if (res.data.code === 200) {
          this.userList = res.data.data
        }
      } catch (error) {
        console.error('获取用户列表失败:', error)
        alert('获取用户列表失败，请检查后端服务是否启动')
      } finally {
        this.listLoading = false
      }
    }
  }
}
</script>

<style scoped>
* {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
}

.user-page {
  min-height: 100vh;
  background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
  font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
}

/* 头部样式 */
.header {
  background: rgba(255, 255, 255, 0.95);
  backdrop-filter: blur(10px);
  box-shadow: 0 2px 20px rgba(0, 0, 0, 0.1);
  padding: 0 40px;
  height: 70px;
  display: flex;
  justify-content: space-between;
  align-items: center;
  position: sticky;
  top: 0;
  z-index: 100;
}

.logo {
  display: flex;
  align-items: center;
  gap: 12px;
}

.logo-icon {
  font-size: 28px;
}

.logo-text {
  font-size: 20px;
  font-weight: bold;
  background: linear-gradient(135deg, #667eea, #764ba2);
  -webkit-background-clip: text;
  background-clip: text;
  color: transparent;
}

.nav {
  display: flex;
  gap: 30px;
}

.nav-link {
  text-decoration: none;
  color: #555;
  font-weight: 500;
  padding: 8px 16px;
  border-radius: 8px;
  transition: all 0.3s ease;
}

.nav-link:hover {
  background: #f0f0f0;
  color: #667eea;
}

.nav-link.active {
  background: linear-gradient(135deg, #667eea, #764ba2);
  color: white;
}

/* 主体内容 */
.main {
  padding: 40px;
  min-height: calc(100vh - 130px);
}

.container {
  max-width: 1400px;
  margin: 0 auto;
  display: grid;
  grid-template-columns: 1fr 1.5fr;
  gap: 30px;
}

/* 卡片样式 */
.card {
  background: white;
  border-radius: 20px;
  box-shadow: 0 20px 40px rgba(0, 0, 0, 0.1);
  overflow: hidden;
  transition: transform 0.3s ease, box-shadow 0.3s ease;
}

.card:hover {
  transform: translateY(-5px);
  box-shadow: 0 30px 50px rgba(0, 0, 0, 0.15);
}

.card-header {
  background: linear-gradient(135deg, #667eea, #764ba2);
  padding: 20px 24px;
  display: flex;
  align-items: center;
  gap: 12px;
  position: relative;
}

.card-icon {
  font-size: 28px;
}

.card-title {
  color: white;
  font-size: 20px;
  font-weight: 600;
  margin: 0;
  flex: 1;
}

.btn-refresh {
  background: rgba(255, 255, 255, 0.2);
  border: none;
  border-radius: 8px;
  padding: 8px 12px;
  font-size: 16px;
  cursor: pointer;
  transition: all 0.3s ease;
}

.btn-refresh:hover {
  background: rgba(255, 255, 255, 0.3);
  transform: rotate(180deg);
}

.card-body {
  padding: 24px;
}

/* 信息展示 */
.info-content {
  background: #f8f9fa;
  border-radius: 12px;
  padding: 20px;
  margin-bottom: 20px;
}

.info-row {
  display: flex;
  padding: 12px 0;
  border-bottom: 1px solid #e9ecef;
}

.info-row:last-child {
  border-bottom: none;
}

.info-label {
  width: 80px;
  font-weight: 600;
  color: #667eea;
}

.info-value {
  flex: 1;
  color: #333;
}

/* 表格样式 */
.table-wrapper {
  overflow-x: auto;
  margin-bottom: 20px;
}

.user-table {
  width: 100%;
  border-collapse: collapse;
}

.user-table thead {
  background: linear-gradient(135deg, #667eea, #764ba2);
  color: white;
}

.user-table th,
.user-table td {
  padding: 14px 16px;
  text-align: left;
  border-bottom: 1px solid #e9ecef;
}

.user-table tbody tr:hover {
  background: #f8f9fa;
}

.user-table td {
  color: #555;
}

/* 按钮样式 */
.btn {
  border: none;
  padding: 12px 28px;
  font-size: 14px;
  font-weight: 600;
  border-radius: 10px;
  cursor: pointer;
  transition: all 0.3s ease;
  margin-right: 12px;
}

.btn-primary {
  background: linear-gradient(135deg, #667eea, #764ba2);
  color: white;
}

.btn-primary:hover:not(:disabled) {
  transform: translateY(-2px);
  box-shadow: 0 5px 15px rgba(102, 126, 234, 0.4);
}

.btn-secondary {
  background: #6c757d;
  color: white;
}

.btn-secondary:hover:not(:disabled) {
  background: #5a6268;
  transform: translateY(-2px);
}

.btn:disabled {
  opacity: 0.6;
  cursor: not-allowed;
}

/* 加载动画 */
.loading {
  text-align: center;
  padding: 40px;
}

.spinner {
  width: 40px;
  height: 40px;
  border: 3px solid #e9ecef;
  border-top-color: #667eea;
  border-radius: 50%;
  animation: spin 1s linear infinite;
  margin: 0 auto 15px;
}

@keyframes spin {
  to {
    transform: rotate(360deg);
  }
}

/* 空状态 */
.empty-state {
  text-align: center;
  padding: 40px;
  color: #adb5bd;
}

/* 底部 */
.footer {
  background: rgba(255, 255, 255, 0.95);
  padding: 20px;
  text-align: center;
  color: #666;
  font-size: 12px;
  border-top: 1px solid rgba(0, 0, 0, 0.05);
}

/* 响应式 */
@media (max-width: 900px) {
  .container {
    grid-template-columns: 1fr;
  }
  
  .header {
    padding: 0 20px;
  }
  
  .logo-text {
    font-size: 16px;
  }
  
  .main {
    padding: 20px;
  }
}
</style>