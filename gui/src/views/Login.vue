<template>
  <div id="login">
    <h1>Payment Gateway</h1>
    <h2>Managed Services</h2>
    <b-form-group width="100%">
      <b-form-input class="my-2" type="email" name="username" v-model="input.username" placeholder="Email" />
      <b-form-input class="my-2" type="password" name="password" v-model="input.password" placeholder="Password" />
      <b-button class="w-100" variant="primary" v-on:click="login()">Login</b-button>
    </b-form-group>
    <br />
    <br />
    <div v-if="msg" class="alert alert-danger" role="alert">
      {{ msg }}
    </div>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  name: 'Login',
  data() {
    return {
      input: {
        username: '',
        password: '',
      },
      response: '',
      msg: '',
      logged_in: false,
    };
  },
  methods: {
    login: function() {
      axios
        .get(
          'http://localhost:8080/ticket/baseAPI/login?', {
            params: {
              username: this.input.username,
              password: this.input.password,
            },
          }, {
            headers: {
              'Access-Control-Allow-Origin': 'http://127.0.0.1',
              'Content-Type': 'application/json',
            },
          },
        )
        .then(response => {
          console.log(response.data);
          if (!response.data.includes('success')) {
            this.msg = 'Login failed. Check your email & password';
          } else {
            this.$emit('authenticated', true);
            this.$router.replace({ name: 'dashboard' });
            this.msg = response.data;

          }
        })
        .catch(function(error) {
          this.msg = error;
          console.log(error);
        })
        .then(function() {
          // always executed
        });
    },
  },
};
</script>

<style scoped>
#login {
  width: 400px;
  border: 1px solid #cccccc;
  background-color: #ffffff;
  margin: auto;
  margin-top: 200px;
  padding: 20px;
}

h1 {
  color: #2c3e50;
}
</style>
