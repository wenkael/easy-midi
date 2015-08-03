Play sound via MIDI

Screenshot

![http://javafx.me/files/emidi.jpg](http://javafx.me/files/emidi.jpg)

Что такое MIDI - это стандарт на команды управления синтезатором или другими устройствами воспроизведения звука. Т.е. в .mp3, например, хранится оцифрованный звук а в файлах .mid только ноты которые воспроизводит звуковая карта.

преимущества MIDI:
есть в любом компе, содержит 128 обычных и 40 ударных инструментов
воспроизводится муз.сопроцессором и не влияет на производительность
воспроизводимую музыку можно интерактивно менять (если понимать чем диез от бемоля отличается)

недостатки:
звучание полностью зависит от звуковой карты и на разных компах может отличаться

для чего можно использовать - для вспомогательных звуковых эффектов. В моей библиотеке есть функции для простого доступа к звуку чтоб не возиться с событиями, каналами и пр.

пример простого вызова
```
Tools.playNote( Note.p93_7_La, Note.i127_Gunshot,127, 2000);
Tools.playDrum(Drum.d39_Hand_Clap, 127, 2000);
```
играет в отдельном потоке. Пример небольшого риффа
```
int bassVoice=Note.i33_Electric_Bass_finger;
Drum hat=new Drum(4, Drum.d42_Closed_Hi_Hat,64);
Drum snare=new Drum(4, Drum.d38_Acoustic_Snare);
Drum bass=new Drum(4, Drum.d35_Acoustic_Bass_Drum);
Phrase p1=new Phrase()                    
	.chord(new Chord(8).drum(hat).drum(bass)	.note(8, Note.p28_2_Mi, bassVoice))
	.chord(new Chord(8).drum(hat)               	.note(8, Note.p28_2_Mi, bassVoice))
	.chord(new Chord(8).drum(hat).drum(snare)	.note(8, Note.p28_2_Mi, bassVoice))
	.chord(new Chord(8).drum(hat)               	.note(8, Note.p28_2_Mi, bassVoice))
	.chord(new Chord(8).drum(hat).drum(bass)	.note(8, Note.p28_2_Mi, bassVoice))
	.chord(new Chord(8).drum(hat).drum(bass)	.note(8, Note.p28_2_Mi, bassVoice))
	.chord(new Chord(8).drum(hat).drum(snare)	.note(8, Note.p34_2_La_Diese, bassVoice))
	.chord(new Chord(8).drum(hat)			.note(8, Note.p35_2_Si, bassVoice))
;
Ticker ti = new Ticker(120, p1);
ti.restart();
```