<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	version="2.0" xmlns:xcl="http://purl.org/xcl/2.0/">
	<xsl:output method="xml" omit-xml-declaration="yes" />
	<xsl:template match="*|@*|text()">
		<xsl:copy>
			<xsl:apply-templates select="*|@*|text()" />
		</xsl:copy>
	</xsl:template>
	<xsl:template match="/*">
		<xsl:element name="xcl:{local-name()}" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
			xmlns:xcl="http://purl.org/xcl/2.0/">
			<xsl:apply-templates select="@*" />
			<xsl:text>
  </xsl:text>
			<xsl:apply-templates select="xcl:Comment" />
			<xsl:text>
  </xsl:text>
			<xsl:comment>
				XML comments should not be parsed.
			</xsl:comment>
			<xsl:text>
  </xsl:text>
			<xsl:apply-templates select="xcl:Prefix[position()=1]" />
			<xsl:text>
  </xsl:text>
			<xsl:apply-templates select="xcl:Prefix[position()=2]" />
			<xsl:text>
  </xsl:text>
			<xsl:apply-templates select="xcl:Name" />
			<xsl:text>
</xsl:text>
		</xsl:element>
	</xsl:template>
</xsl:stylesheet>