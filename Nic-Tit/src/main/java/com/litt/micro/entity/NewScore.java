package com.litt.micro.entity;

public class NewScore {
		private String course_id;
		private String course_name;
		private String score;
		private String gpa;
		
		public String getCourse_id() {
			return course_id;
		}
		public void setCourse_id(String course_id) {
			this.course_id = course_id;
		}
		public String getCourse_name() {
			return course_name;
		}
		public void setCourse_name(String course_name) {
			this.course_name = course_name;
		}
		public String getScore() {
			return score;
		}
		public void setScore(String score) {
			this.score = score;
		}
		public String getGpa() {
			return gpa;
		}
		public void setGpa(String gpa) {
			this.gpa = gpa;
		}
		@Override
		public String toString() {
			return "NewScore [course_id=" + course_id + ", course_name=" + course_name + ", score=" + score + ", gpa="
					+ gpa + "]";
		}	
}
