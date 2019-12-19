<?xml version="1.0" encoding="UTF-8"?>

<xsl:stylesheet version="1.0"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output method="html" indent="yes"/>
    <xsl:template match="/groupList">
        <html>
            <body>
                <a href="/xslt/teachers">Teachers</a><br/>
                <a href="/xslt/lessons">Lessons</a><br/>
                <table align="center">
                    <thead>
                        <th>Name</th>
                    </thead>
                    <tbody>
                        <xsl:for-each select="groups">
                            <tr>
                                <td><xsl:value-of select="name"/></td>
                            </tr>
                        </xsl:for-each>
                    </tbody>

                </table>
            </body>
        </html>
</xsl:template>
</xsl:stylesheet>