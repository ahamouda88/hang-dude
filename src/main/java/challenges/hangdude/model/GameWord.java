package challenges.hangdude.model;

/**
 * A POJO class that represents a game word
 * 
 * @author ahamouda
 */
public class GameWord {

	private String word;
	private Difficulty difficulty;
	private Category category;

	private GameWord(Builder builder) {
		this.word = builder.word;
		this.difficulty = builder.difficulty;
		this.category = builder.category;
	}

	public String getWord() {
		return word;
	}

	public Difficulty getDifficulty() {
		return difficulty;
	}

	public Category getCategory() {
		return category;
	}

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder {
		private String word;
		private Difficulty difficulty;
		private Category category;

		public Builder word(String word) {
			this.word = word;
			return this;
		}

		public Builder difficulty(Difficulty difficulty) {
			this.difficulty = difficulty;
			return this;
		}

		public Builder category(Category category) {
			this.category = category;
			return this;
		}

		public GameWord build() {
			return new GameWord(this);
		}

	}

}
