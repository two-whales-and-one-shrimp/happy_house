<template>
  <div>
    <search-bar></search-bar>
    <news-list></news-list>
  </div>
</template>

<script>
import searchBar from "@/components/home/SearchBar";
import newsList from "@/components/home/NewsList";
export default {
  name: "Home",
  components: {
    searchBar,
    newsList,
  },
};
</script>
