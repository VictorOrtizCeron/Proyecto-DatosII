<template>

  <div class="container-fluid">
    <div class="input-group">

      <textarea class="form-control" aria-label="With textarea" v-model="query"> </textarea>


    </div>
    <button type="button" class="btn btn-outline-success" @click="sendQuery">Query</button>
    <button type="button" class="btn btn-outline-danger">Commit</button>

    <!-- Button trigger modal -->
    <button type="button" class="btn btn-outline-primary" data-bs-toggle="modal" data-bs-target="#staticBackdrop">
      Crear Tabla
    </button>

    <!-- Modal -->
    <div class="modal fade " id="staticBackdrop" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1"
         aria-labelledby="staticBackdropLabel" aria-hidden="true">
      <div class="modal-dialog modal-dialog modal-dialog-centered modal-dialog-scrollable">
        <div class="modal-content">
          <div class="modal-header">
            <h1 class="modal-title fs-5" id="staticBackdropLabel">Información de la tabla</h1>
            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
          </div>
          <div class="modal-body">

            <input type="text" class="form-control popup" v-model="newTableName" placeholder="Nombre de la tabla">


            <textarea class="form-control popup" aria-label="With textarea" placeholder="Columnas de la tabla"
                      v-model="newTableColumns"> </textarea>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-outline-secondary" data-bs-dismiss="modal">Cancelar</button>
            <button type="button" class="btn btn-primary" @click="sendColumnData">Crear Tabla</button>
          </div>
        </div>
      </div>
    </div>


  </div>

  <div class="container-fluid">
    <div class=" card">
      <h5 class="card-title">Tabla de Output</h5>
      <table class="table table-bordered">
        <thead>
        <tr >
          <th v-for="item in newTableColumns" :key="item.id" scope="col">{{item}} </th>

        </tr>
        </thead>
        <tbody>
        <tr v-for= "(row,rowIndex) in tableData.length" :key="rowIndex">
          <td v-for="(value, colIndex) in tableData" :key="colIndex">{{ value[rowIndex] }}</td><!-- Add more table cells as needed -->
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
      tableData: [],
      query: '',
      newTableName: '',
      newTableColumns: ''
    }
  },
  methods: {
    sendQuery() {
      axios.post("/api/database/table", {
        query: this.query
      })
          .then((response) => {
                this.newTableColumns = response.data.headers;
                this.tableData = response.data.items;
              }
          ).catch((error) => {
        console.error("Error sending text:", error);
        // Handle the error if needed
      });
    },
    sendColumnData() {
      axios.post("/api/database/create", {
        newTableName: this.newTableName,
        newTableColumns: this.newTableColumns
      })
          .then((response) => {
            alert(response.data);
          }
      ).catch((error) => {
        console.error("Error sending text:", error);
      })
    }
  },

}
</script>

<style scoped>
.card {

  padding: 50px;
}

.btn-outline-success, .btn-outline-danger, .btn-outline-primary {
  width: 10%;
  margin: 5px;
}

.form-control {
  font-size: 1.5rem;
}

.popup {
  margin-top: 10px;
}

textarea {
  resize: none;
  height: 350px;
}
</style>