<template>

  <div class="container-fluid">
    <div class="input-group">

      <textarea class="form-control" aria-label="With textarea" v-model="query"> </textarea>


    </div>
    <button type="button" class="btn btn-outline-success" @click="sendQuery">Query</button>
    <button type="button" class="btn btn-outline-danger">Commit</button>

  </div>

  <div class="container-fluid">
    <div class=" card">
      <h5 class="card-title">Tabla de Output</h5>
      <table class="table table-bordered">
        <thead>
        <tr>
          <th scope="col">First Name</th>
          <th scope="col">Last Name</th>
          <th scope="col">Age</th>
        </tr>
        </thead>
        <tbody>
        <tr v-for="item in tableData" :key="item.id">
          <td>{{ item.first_name }}</td>
          <td>{{ item.last_name }}</td>
          <td>{{ item.age }}</td>
          <!-- Add more table cells as needed -->
        </tr>
        </tbody>
      </table>
    </div>
  </div>

</template>

<script>


import axios from "axios";

export default {
  name: 'tableOutput',
  data() {
    return {
      tableData: [
        {
          first_name: 'victor',
          last_name: 'ortiz',
          age: '22'
        },
        {
          first_name: 'raul',
          last_name: 'ortiz',
          age: '23'
        },

      ],
      query: '',
    }
  },
  methods: {
    sendQuery() {
      axios.post("/api/database/table", {
        query: this.query
      })
          .then((response) => {
                alert(response.data);
              }
          ).catch((error) => {
        console.error("Error sending text:", error);
        // Handle the error if needed
      });
    },
  },

}
</script>

<style scoped>
.card {

  padding: 50px;
}
.btn-outline-success, .btn-outline-danger {
  width: 10%;
  margin: 5px;
}

.form-control {
  font-size: 1.5rem;
}

textarea {
  resize: none;
  height: 350px;
}
</style>