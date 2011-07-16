package midi;

import java.util.*;

public class Ticker {

	Timer timer;
	public int counter16 = 0;
	Phrase phrase = null;
	int currentChord = 0;
	int tempo = 120;
	int delay16 = 0;
	boolean on = false;

	public Ticker(int tempo, Phrase p) {
		timer = new Timer(true);
		phrase = p;
		this.tempo = tempo;
		delay16 = (int) (10000.0 / tempo);
		timer.schedule(new TimerTask() {

			@Override
			public void run() {
				if (on) {
					counter16++;
					tick();
					onTick(counter16);
				}
			}
		}, 0, delay16);
	}

	public void onTick(long count) {
	}

	public void onFinish() {
		restart();
	}

	void tick() {
		if (phrase == null) {
			return;
		}
		if (phrase.chords == null) {
			return;
		}
		if (currentChord >= phrase.chords.size()) {
			onFinish();
		}
		if (currentChord >= phrase.chords.size()) {
			return;
		}
		phrase.chords.get(currentChord).counter16++;
		if (phrase.chords.get(currentChord).counter16 / 16.0 > 1.0 / phrase.chords.get(currentChord).part) {
			currentChord++;
			if (currentChord >= phrase.chords.size()) {
				onFinish();
			} else {
				phrase.chords.get(currentChord).play();
			}
		} else {
			for (int i = 0; i < phrase.chords.size(); i++) {
				phrase.chords.get(i).tick();
			}
		}
	}

	public void play() {
		on = true;
	}

	public void restart() {
		counter16 = 0;
		currentChord = 0;
		on = true;
		if (currentChord >= phrase.chords.size()) {
			return;
		}
		phrase.chords.get(currentChord).play();
	}

	public void stop() {
		on = false;
		for (int i = 0; i < phrase.chords.size(); i++) {
			phrase.chords.get(i).stop();
		}
	}
}