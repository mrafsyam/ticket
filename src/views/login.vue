<template>
  <div id="login">
    <h1>Ticket Tracking</h1>
    <label for="username">Email</label>
    <input
      type="text"
      name="username"
      v-model="input.username"
      placeholder="Email"
    />
    <label for="password">Password</label>
    <input
      type="password"
      name="password"
      v-model="input.password"
      placeholder="Password"
    />
    <button type="button" v-on:click="login()">Login</button>
    <br />
    <br />
    <div v-if="msg" class="alert alert-danger" role="alert">
      {{ msg }}
    </div>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "Login",
  data() {
    return {
      input: {
        username: "",
        password: ""
      },
      response: "",
      msg: ""
    };
  },
  methods: {
    login: function() {
      axios
        .get("http://localhost:8080/baseAPI/login?", {
          params: {
            username: this.input.username,
            password: this.input.password
          }
        })
        .then(response => {
          console.log(response.data);
          if (!response.data.includes("success")) {
            this.msg = "Login failed. Check your email & password";
          } else {
            // what is this "emit"?
            this.$emit("authenticated", true);
            // reroute to authenticated page
            this.$router.replace({ name: "secure" });
          }
        })
        .catch(function(error) {
          console.log(error);
        })
        .then(function() {
          // always executed
        });
    }
  }
};
</script>

<style scoped>
#login {
  width: 300px;
  border: 1px solid #cccccc;
  background-color: #ffffff;
  margin: auto;
  margin-top: 200px;
  padding: 20px;
}
</style>
