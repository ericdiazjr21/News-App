package ericdiaz.program.newsapp.model

data class NewsResponse(
    val status: String,
    val totalResults: Int,
    val articles: List<Article>
) {
    companion object {
        val EMPTY = NewsResponse("test", 100, emptyList())
    }
}

data class Article(
    val source: Source,
    val author: String,
    val title: String,
    val description: String,
    val url: String,
    val urlToImage: String,
    val publishedAt: String,
    val content: String
)

data class Source(
    val id: String,
    val name: String
)