package com.epam.task.textHandler.parser;

import com.epam.task.textHanling.entities.Component;
import com.epam.task.textHanling.entities.Composite;
import com.epam.task.textHanling.entities.Lexeme;
import com.epam.task.textHanling.parser.ParagraphParser;
import com.epam.task.textHanling.parser.Parser;
import com.epam.task.textHanling.parser.SentenceParser;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Arrays;

import static org.mockito.Mockito.when;

public class ParagraphParserTest {

    private static final String PARAGRAPH_TEXT = "First sentence. Second sentence. ";

    @Test
    public void parseShouldReturnComponentsSentences() {
        // given
        Lexeme lexemeFirst = Lexeme.word("First");
        Lexeme lexemeSecond = Lexeme.word("sentence");
        Lexeme lexemeThird = Lexeme.word("Second");
        Lexeme lexemeFourth = Lexeme.word("sentence");
        Component sentenceFirst = new Composite(Arrays.asList(lexemeFirst, lexemeSecond));
        Component sentenceSecond = new Composite(Arrays.asList(lexemeThird, lexemeFourth));
        Component expectedResult = new Composite(Arrays.asList(sentenceFirst, sentenceSecond));

        Parser sentenceParser = Mockito.mock(SentenceParser.class);
        ParagraphParser paragraphParser = new ParagraphParser(sentenceParser);

        String sentenceFirstAsText = "First sentence";
        when(sentenceParser.parse(sentenceFirstAsText)).thenReturn(sentenceFirst);

        String sentenceSecondAsText = "Second sentence";
        when(sentenceParser.parse(sentenceSecondAsText)).thenReturn(sentenceSecond);

        //when
        Component actualResult = paragraphParser.parse(PARAGRAPH_TEXT);

        //then
        Assert.assertEquals(expectedResult, actualResult);

    }

}
