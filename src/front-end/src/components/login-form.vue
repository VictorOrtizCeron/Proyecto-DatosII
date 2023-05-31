<template>

  <h1 >MY DATABASE CE</h1>
  <h1 >Login Page </h1>
  <div class = "card">

  <div class = "container-fluid">

      <form>
        <div class="mb-3">
          <label for="exampleInputEmail1" class = "form-label">Username</label>
          <input type="text" class = "form-control" v-model="textFieldValue" placeholder="Enter Username">
        </div>
        <div class="mb-3">
          <label for="exampleInputPassword1" class = "form-label" >Password</label>
          <input type="password" class="form-control" v-model="passwordFieldValue" id="exampleInputPassword1"
                 placeholder="Password">
        </div>
        <div class = "mb-3">

        <button type="submit" @click="sendText" class="btn btn-primary">Submit</button>
        </div>
      </form>

    </div>
  </div>

</template>

<script>
import axios from "axios";

export default {
  name:'loginform',
  data() {
    return {
      textFieldValue: "",
      passwordFieldValue: "",
    };
  },
  methods: {
    sendText() {
      axios.post("api/messages/login", {
        username: this.textFieldValue ,
        password: this.passwordFieldValue
      })
          .then((response) => {
            alert("Text sent successfully!");
            // Handle the response from the backend if needed
          })
          .catch((error) => {
            console.error("Error sending text:", error);
            // Handle the error if needed
          });
    },
  },
};
</script>


<style scoped>

h1{
  text-align: center;
  padding-bottom: 1.5rem;
  padding-top: 1.5rem;
}
.card{
  max-width: 30%;

  padding: 10px 10px;
  margin:auto;
}
</style>