package html;

public class HtmlUtility {
	public static String htmlBody(String title) {
		return "<html><title>"+title+"</title><body style=\"text-align:center;\">";
	}

	public static String backButton() {
		return "<form style=\"text-align:left; padding-left:10px\"><input type=\"button\" value=\"<-Go Back\" onclick=\"history.back()\"></form>";
	}

	public static String homeButton() {
		return "<form action=\"index.jsp\" style=\"text-align:left; padding-left:10px\"><input type=\"submit\" value=\"Choose System\"></form>";
	}

	public static String adjacentBackHome() {
		return "<div style=\"width:440px\">" + "<div style=\"float: left; width: 130px\">" + backButton() + "</div>\n"
				+ "<div style=\"float: right; width: 270px\"> \n" + homeButton() + "</div>\n" + "</div>";
	}

	public static String htmlBodyClose() {
		return "</html></body>";
	}

}
