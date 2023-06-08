<template>
  <img class="image" src="https://icons.iconarchive.com/icons/paomedia/small-n-flat/256/database-icon.png">
  <h1>MyDatabaseCE</h1>
  <h1>Login Page </h1>
  <div class="card">

    <div class="container-fluid">

      <form>
        <div class="mb-3">
          <label for="exampleInputEmail1" class="form-label">Username</label>
          <input type="text" class="form-control" v-model="textFieldValue" placeholder="Enter Username">
        </div>
        <div class="mb-3">
          <label for="exampleInputPassword1" class="form-label">Password</label>
          <input type="password" class="form-control" v-model="passwordFieldValue" id="exampleInputPassword1"
                 placeholder="Password">
        </div>
        <div class="mb-3">

          <button type="submit" @click="sendText" class="btn btn-primary">Login</button>
          <button type="submit" @click="sendRegistration" class="btn btn-outline-success">Sign in</button>
        </div>
      </form>

    </div>
  </div>
</template>

<script>
import axios from "axios";

import databaseView from "@/components/database-view.vue";


export default {
  name: 'login-form',
  data() {
    return {
      textFieldValue: "",
      passwordFieldValue: "",
    };
  },
  methods: {
    sendText() {
      axios.post("api/messages/login", {
        username: this.textFieldValue,
        password: this.passwordFieldValue
      })
          .then((response) => {


                if (response.data === "Funciono") {

                  window.location.href = '/database';
                }

              }
              // Handle the response from the backend if needed

          )
          .catch((error) => {
            console.error("Error sending text:", error);
            // Handle the error if needed
          });
    },
    sendRegistration() {
      axios.post("api/messages/register", {
        username: this.textFieldValue,
        password: this.passwordFieldValue
      })
          .then((response) => {


                if (response.data === "Funciono") {

                  window.location.href = '/database';
                }

              }
              // Handle the response from the backend if needed

          )
          .catch((error) => {
            console.error("Error sending text:", error);
            // Handle the error if needed
          });
    }

  },
};
</script>


<style scoped>

h1 {
  text-align: center;
  padding-bottom: 1.5rem;
  padding-top: 1.5rem;
}

.image {
  margin-top: 30px;
  max-width: 10%;
}

.card {
  max-width: 30%;

  padding: 10px 10px;
  margin: auto;
}

.btn {
  margin: 10px;
  width: 40%;
}
</style>