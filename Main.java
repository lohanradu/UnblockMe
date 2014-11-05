import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.List;

public class Main {

	private static InputReader reader = new InputReader();

	public static void main(String[] args) throws FileNotFoundException {
		reader.readInput();

		Board board = reader.board;
		board.addBlocks(reader.blocks);

		board.play();

		List<Step> steps = reader.steps;
		int stepIndex = 0;
		for (Step step : steps) {
			if (board.checkStepValid(step)) {
				board.makeStep(step);
				stepIndex++;
			} else {
				break;
			}
		}
		System.out.println(stepIndex);

		List<Integer> result = board.getUnmovableBlocks();
		Collections.sort(result);

		for (Integer id : result) {
			System.out.print(id);
			System.out.print(" ");
		}
		System.out.println();
	}
}
