package first.sample.controller;

public class JsonOutput {
		String result;
		String reason;
		
		public String getResult() {
			return result;
		}

		public void setResult(String result) {
			this.result = result;
		}

		public String getReason() {
			return reason;
		}

		public void setReason(String reason) {
			this.reason = reason;
		}

		@Override
		public String toString() {
			return "JsonOutput [result=" + result + ", reason=" + reason +"]";
		}
	
}
