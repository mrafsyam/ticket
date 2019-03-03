<template>
    <div id="login">
        <h1>Login</h1>
        <input type="text" name="username" v-model="input.username" placeholder="Username" />
        <input type="password" name="password" v-model="input.password" placeholder="Password" />
        <button type="button" v-on:click="login()">Login</button>
 	<br />
        <br />
        <div id=alert class="alert alert-danger" role="alert">
	    {{ msg }}
	</div>
    </div>
</template>

<script>
    import axios from "axios";

    export default {
        name: 'Login',
        data() {
            return {
                input: {
                    username: "",
                    password: ""
                },
                response: "",
                msg: "Alert : "
            }
        },
        methods: {
            login() {
		axios.get('http://localhost:8080/baseAPI/login?', {
    			params: {
      			    username: this.input.username,
  			    password : this.input.password
   			}
  		})
  		.then(function (response) {
    			console.log(response.data);
                        if (!response.data.includes("success")){
	      		    document.getElementById('alert').innerHTML = "Failed login"

			};
  		})
  		.catch(function (error) {
    			console.log(error);
  		})
  		.then(function () {
    			// always executed
  		}); 
            }
        }
    }
</script>

<style scoped>
    #login {
        width: 500px;
        border: 1px solid #CCCCCC;
        background-color: #FFFFFF;
        margin: auto;
        margin-top: 200px;
        padding: 20px;
    }

    textarea {
        width: 400px;
        height: 200px;
    }
</style>
