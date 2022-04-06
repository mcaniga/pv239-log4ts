package cz.muni.aqicheck.webservice.response

// TODO 2.1 Webservice Data Layer
data class AqiListResponse(
    val status: String,
    val data: List<AqiListItem>
)

data class AqiListItem(
    val uid: Long,
    val aqi: String,
    val time: TimeResponse,
    val station: StationResponse
)

data class TimeResponse(
    val tz: String,
    val stime: String,
    val vtime: String,
)

data class StationResponse(
    val name: String,
    val geo: List<Double>,
    val url: String?
)