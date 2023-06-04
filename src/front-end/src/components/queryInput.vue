<template>
  <div class="container-fluid">
    <div class="input-group">

      <textarea class="form-control" aria-label="With textarea" v-model="query"> </textarea>


    </div>
    <button type="button" class="btn btn-outline-success" @click = "sendQuery">Query</button>
    <button type="button" class="btn btn-outline-danger">Commit</button>

  </div>
</template>

<script>
import axios from "axios";

export default {
  name: 'queryInput',
  data() {
    return {
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
