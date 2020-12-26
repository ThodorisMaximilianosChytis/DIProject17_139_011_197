<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:fo="http://www.w3.org/1999/XSL/Format">
<xsl:output method="text" omit-xml-declaration="yes" indent="no"/>
<xsl:template match="/">
time, id, long, lat, angle, speed, RSSI, Throughput
<xsl:for-each select="//timestep/vehicle">
<xsl:value-of select="concat(../@time, ',' ,@id,',',@x,',',@y,',',@angle,',',@speed,',' ,'0', ',' ,'0',  '&#xA;')"/>
</xsl:for-each>	
</xsl:template>
</xsl:stylesheet>

