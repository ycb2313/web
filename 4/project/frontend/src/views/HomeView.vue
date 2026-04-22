<template>
  <div class="home">
    <div class="hero">
      <div class="hero-content">
        <h1 class="hero-title">🎉 SSM + Vue 前后端整合实验</h1>
        <p class="hero-subtitle">Spring MVC + Spring + MyBatis 与 Vue.js 前后端分离项目</p>
        <div class="hero-buttons">
          <router-link to="/user" class="btn-hero">开始体验 →</router-link>
        </div>
      </div>
    </div>
    
    <div class="features">
      <div class="feature-card">
        <div class="feature-icon">🚀</div>
        <h3>前后端分离</h3>
        <p>前端使用 Vue.js，后端使用 SSM 框架，独立开发，独立部署</p>
      </div>
      <div class="feature-card">
        <div class="feature-icon">📡</div>
        <h3>RESTful API</h3>
        <p>后端提供标准 RESTful 接口，返回 JSON 格式数据</p>
      </div>
      <div class="feature-card">
        <div class="feature-icon">🗄️</div>
        <h3>数据库集成</h3>
        <p>使用 MyBatis 操作 MySQL 数据库，实现数据持久化</p>
      </div>
      <div class="feature-card">
        <div class="feature-icon">🌐</div>
        <h3>跨域支持</h3>
        <p>配置跨域过滤器，支持前后端不同端口通信</p>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'HomeView'
}
</script>

<style scoped>
.home {
  min-height: calc(100vh - 70px);
}

.hero {
  background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
  padding: 80px 40px;
  text-align: center;
  color: white;
}

.hero-content {
  max-width: 800px;
  margin: 0 auto;
}

.hero-title {
  font-size: 48px;
  margin-bottom: 20px;
}

.hero-subtitle {
  font-size: 20px;
  margin-bottom: 40px;
  opacity: 0.95;
}

.btn-hero {
  display: inline-block;
  background: white;
  color: #667eea;
  padding: 14px 40px;
  border-radius: 50px;
  text-decoration: none;
  font-weight: 600;
  transition: all 0.3s ease;
}

.btn-hero:hover {
  transform: translateY(-3px);
  box-shadow: 0 10px 20px rgba(0, 0, 0, 0.2);
}

.features {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(250px, 1fr));
  gap: 30px;
  padding: 60px 40px;
  max-width: 1200px;
  margin: 0 auto;
}

.feature-card {
  text-align: center;
  padding: 30px;
  background: white;
  border-radius: 16px;
  box-shadow: 0 5px 20px rgba(0, 0, 0, 0.05);
  transition: all 0.3s ease;
}

.feature-card:hover {
  transform: translateY(-5px);
  box-shadow: 0 15px 30px rgba(0, 0, 0, 0.1);
}

.feature-icon {
  font-size: 48px;
  margin-bottom: 20px;
}

.feature-card h3 {
  font-size: 20px;
  margin-bottom: 12px;
  color: #333;
}

.feature-card p {
  color: #666;
  line-height: 1.6;
}

@media (max-width: 768px) {
  .hero-title {
    font-size: 32px;
  }
  
  .hero-subtitle {
    font-size: 16px;
  }
  
  .features {
    padding: 40px 20px;
  }
}
</style>