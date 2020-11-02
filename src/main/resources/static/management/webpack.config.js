const path = require('path')
const HtmlWebPackPlugin = require('html-webpack-plugin')

module.exports = {
    output: {
        path: path.resolve(__dirname, 'build'),
        publicPath: '/',
        filename: '[name].[chunkhash].js',
    },
    resolve: {
        modules: [path.join(__dirname, 'src'), 'node_modules']
    },
    module: {
        rules: [
            {
                test: /\.(js|jsx)$/,
                exclude: /node_modules/,
                use: {
                    loader: 'babel-loader',
                },
            },
            {
                test: /\.css$/,
                use: [
                    {
                        loader: 'style-loader',
                    },
                    {
                        loader: 'css-loader',
                    },
                ],
            },
            {
                test: /\.(jpe?g|png|gif|svg)$/i,
                loader: "file-loader"
            }
        ],
    },
    plugins: [
        new HtmlWebPackPlugin({
            template: './src/public/index.html',
        }),
    ],
    devServer: {
        contentBase: './src/public',
        historyApiFallback: true
    }
}
