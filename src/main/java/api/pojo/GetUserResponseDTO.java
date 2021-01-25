package api.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GetUserResponseDTO {

	@JsonProperty("data")
	private Data data;
	@JsonProperty("support")
	private Support support;

	@JsonProperty("data")
	public Data getData() {
		return data;
	}

	@JsonProperty("data")
	public void setData(Data data) {
		this.data = data;
	}

	@JsonProperty("support")
	public Support getSupport() {
		return support;
	}

	@JsonProperty("support")
	public void setSupport(Support support) {
		this.support = support;
	}

	public static class Data {

		@JsonProperty("id")
		private Integer id;
		@JsonProperty("name")
		private String name;
		@JsonProperty("year")
		private Integer year;
		@JsonProperty("color")
		private String color;
		@JsonProperty("color")
		private String pantone_value;
		@JsonProperty("pantone_value")
		private String email;
		@JsonProperty("first_name")
		private String firstName;
		@JsonProperty("last_name")
		private String lastName;
		@JsonProperty("avatar")
		private String avatar;

		@JsonProperty("id")
		public Integer getId() {
			return id;
		}

		@JsonProperty("name")
		public void setName(String name) {
			this.name = name;
		}
		
		@JsonProperty("name")
		public String getName() {
			return name;
		}
		
		@JsonProperty("year")
		public void setYear(Integer year) {
			this.year = year;
		}
		
		@JsonProperty("year")
		public Integer getYear() {
			return year;
		}
		
		@JsonProperty("color")
		public void setColor(String color) {
			this.color = color;
		}
		
		@JsonProperty("color")
		public String getColor() {
			return color;
		}
		
		@JsonProperty("pantone_value")
		public void setPantone_value(String pantone_value) {
			this.pantone_value = pantone_value;
		}
		
		@JsonProperty("pantone_value")
		public String getPantone_value() {
			return pantone_value;
		}

		@JsonProperty("id")
		public void setId(Integer id) {
			this.id = id;
		}

		@JsonProperty("email")
		public String getEmail() {
			return email;
		}

		@JsonProperty("email")
		public void setEmail(String email) {
			this.email = email;
		}

		@JsonProperty("first_name")
		public String getFirstName() {
			return firstName;
		}

		@JsonProperty("first_name")
		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}

		@JsonProperty("last_name")
		public String getLastName() {
			return lastName;
		}

		@JsonProperty("last_name")
		public void setLastName(String lastName) {
			this.lastName = lastName;
		}

		@JsonProperty("avatar")
		public String getAvatar() {
			return avatar;
		}

		@JsonProperty("avatar")
		public void setAvatar(String avatar) {
			this.avatar = avatar;
		}

	}

	public class Support {

		@JsonProperty("url")
		private String url;
		@JsonProperty("text")
		private String text;

		@JsonProperty("url")
		public String getUrl() {
			return url;
		}

		@JsonProperty("url")
		public void setUrl(String url) {
			this.url = url;
		}

		@JsonProperty("text")
		public String getText() {
			return text;
		}

		@JsonProperty("text")
		public void setText(String text) {
			this.text = text;
		}

	}
}
