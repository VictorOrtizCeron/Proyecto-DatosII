<template>
  <div class="container-fluid">
    <div class="input-group">

      <textarea class="form-control" aria-label="With textarea">
      </textarea>


    </div>
    <button type="button" class="btn btn-outline-success">Query</button>
    <button type="button" class="btn btn-outline-danger">Commit</button>

  </div>
</template>

<script>
export default {
  name: 'queryInput',

}
</script>

<style scoped>
.btn-outline-success, .btn-outline-danger{
  width: 10%;
  margin: 5px;
}
.form-control{
  font-size: 1.5rem;
}
textarea{
  resize: none;
  height: 350px;
}
</style>
