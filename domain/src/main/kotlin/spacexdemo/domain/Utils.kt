package spacexdemo.domain

import java.time.*

fun yearToEpochSeconds(
    year: Int,
    zoneId: ZoneId = ZoneId.systemDefault()
): Long =
    LocalDateTime.of(year, Month.JANUARY, 1, 0, 0).toEpochSecond(zoneId.rules.getOffset(Instant.now()))