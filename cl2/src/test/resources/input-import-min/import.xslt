<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	version="2.0">
	<xsl:output method="xml" omit-xml-declaration="yes" />
	<xsl:template match="*|@*|text()">
		<xsl:copy>
			<xsl:apply-templates select="*|@*|text()" />
		</xsl:copy>
	</xsl:template>
	<xsl:template match="/*">
		<xsl:element name="{local-name()}" xmlns="http://purl.org/xcl/2.0/"
			xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:xcl="http://purl.org/xcl/2.0/">
			<xsl:apply-templates select="@*" />
			<xsl:text>
  </xsl:text>
			<xsl:apply-templates select="xcl:Name" />
			<xsl:text>    
</xsl:text>
		</xsl:element>
	</xsl:template>
</xsl:stylesheet>