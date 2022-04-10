package cz.muni.log4ts.repository

import cz.muni.log4ts.data.AqiPresentableListItem
import cz.muni.log4ts.data.entities.LogEntry
import cz.muni.log4ts.data.ui.LogEntriesItem
import cz.muni.log4ts.database.FavoriteStationEntity
import cz.muni.log4ts.webservice.response.AqiListItem

fun LogEntry.toLogEntriesItem(): LogEntriesItem =
    LogEntriesItem(
        id = this.id,
        name = this.name,
        project = this.project,
        loggedSeconds = this.loggedSeconds
    )

fun AqiListItem.toAqiPresentableItem(isFavorite: Boolean): AqiPresentableListItem =
    AqiPresentableListItem(
        id = this.uid,
        aqi = this.aqi,
        time = this.time.stime,
        station = this.station.name,
        isFavorite = isFavorite
    )

fun AqiPresentableListItem.toFavoriteStationEntity(): FavoriteStationEntity =
    FavoriteStationEntity(
        id = this.id,
        lastKnownAqi = this.aqi,
        lastSyncTime = this.time,
        station = this.station,
    )

fun FavoriteStationEntity.toAqiPresentableItem() =
    AqiPresentableListItem(
        id = this.id,
        aqi = this.lastKnownAqi,
        time = this.lastSyncTime,
        station = this.station,
        isFavorite = true,
    )