<template>
  <div id="nav">
    <router-link to="/dashboard">Dashboard</router-link> |
    <router-link to="/ticket">Ticket</router-link> |
    <router-link to="/followup">Follow Up</router-link> |
    <router-link to="/incident">Incident</router-link> |
    <router-link to="/downtime">Downtime</router-link> |
    <router-link to="/cr">CR</router-link> |
    <router-link to="/about">About</router-link> |
    <router-link to="/">Logout</router-link>
    </br>
    </br>
    </br>
    <h1>PG Managed Services</h1>
    <img :class="{ full: fullWidthImage }" @click="fullWidthImage = !fullWidthImage" src="https://cdn.statcdn.com/CMS/GettingStartedCMS/3a41c483c55a83b6e228d00410a3dced.png" alt="" />
  </div>
</template>

<script>
export default {
  name: 'home',
  data() {
    return {

    }
  }
}
</script>

<style scoped>
img {
  width: 1050px;
  border-radius: 2px;
  box-shadow: 1px 1px 3px 1px rgba(0, 0, 0, 0.5);
  transition: width 1s;
}
</style>
