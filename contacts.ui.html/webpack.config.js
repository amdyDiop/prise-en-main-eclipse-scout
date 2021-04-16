const baseConfig = require('@eclipse-scout/cli/scripts/webpack-defaults');

module.exports = (env, args) => {
  args.resDirArray = ['src/main/resources/WebContent', 'node_modules/@eclipse-scout/core/res'];
  const config = baseConfig(env, args);

  config.entry = {
    'contacts': './src/main/js/contacts.js',
    'login': './src/main/js/login.js',
    'logout': './src/main/js/logout.js',
    'contacts-theme': './src/main/js/contacts-theme.less',
    'contacts-theme-dark': './src/main/js/contacts-theme-dark.less'
  };

  return config;
};
