<template>
  <nav class="navbar bg-body-tertiary">
    <div class="container-fluid">

      <a class="navbar-brand" href="#">
        <img src="https://icons.iconarchive.com/icons/paomedia/small-n-flat/256/database-icon.png" alt="Logo" width="30"
             height="24" class="d-inline-block align-text-top">
        MyDatabaseCE

      </a>
      <button class="btn btn-primary" type="button" data-bs-toggle="offcanvas"
              data-bs-target="#offcanvasWithBothOptions"
              aria-controls="offcanvasScrolling" @click = "getXmlStores">XML Store
      </button>

    </div>
  </nav>


  <div class="offcanvas offcanvas-start" data-bs-scroll="true" tabindex="-1" id="offcanvasWithBothOptions"
       aria-labelledby="offcanvasWithBothOptionsLabel">
    <div class="offcanvas-header">
      <h5 class="offcanvas-title" id="offcanvasWithBothOptionsLabel">XML Stores</h5>
      <button type="button" class="btn-close" data-bs-dismiss="offcanvas" aria-label="Close"></button>
    </div>
    <div class="offcanvas-body">
      <ul class="list-group ">
        <li v-for="item in xmlStores" :key="item.id" class="list-group-item">
            {{item}}
        </li>

      </ul>
    </div>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: 'sidebar',
  data() {
    return {
      xmlStores: []
    }
  },
  methods: {
    getXmlStores(){
      axios.get("/api/database/sidebar/getInfo")
          .then((response)=>{
            this.xmlStores = response.data.xmlStores;
          })
          .catch(error => {
            console.error("Error fetching data:", error);
            // Handle the error if needed
          });
    }
  }
}
</script>