import Vue from "vue";
import Vuetify from "vuetify/lib/framework";
import colors from "vuetify/lib/util/colors";

Vue.use(Vuetify);

export default new Vuetify({
  theme: {
    themes: {
      light: {
        primary: colors.blue.lighten1,
        black: colors.grey.darken3,
        grey: colors.grey.lighten4,
        white: colors.grey.lighten5,
      },
    },
  },
});
