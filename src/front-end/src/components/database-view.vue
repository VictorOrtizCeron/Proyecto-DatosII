<template>

  <sidebar></sidebar>
  <query-input></query-input>
  <tableOutput></tableOutput>

</template>

<script>

import Sidebar from "@/components/sidebar.vue";

import QueryInput from "@/components/queryInput.vue";
import tableOutput from "@/components/table-output.vue";


export default {
  name: 'databaseView',
  components: {QueryInput, Sidebar, tableOutput },

}
</script>