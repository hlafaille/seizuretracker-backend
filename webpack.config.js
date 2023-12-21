const path = require('path')
const MiniCssExtractPlugin = require('mini-css-extract-plugin')

module.exports = {
    entry: './src/main/js/app.js',
    mode: 'production',
    module: {
        rules: [
            {
                test: /\.css$/,
                use: [
                    MiniCssExtractPlugin.loader,
                    'css-loader',
                    {
                        loader: 'postcss-loader',
                        options: {
                            postcssOptions: {
                                config: path.resolve(__dirname, 'postcss.config.js'),
                            },
                        },
                    },
                ],
            },
        ],
    },
    plugins: [
        require('autoprefixer'),
        require('tailwindcss'),
        new MiniCssExtractPlugin(
            {
                filename: 'bundle.css',
            },
        ),
    ],
    output: {
        filename: 'bundle.js',
        path: path.resolve(__dirname, '.generatedResources'),
    },
}
