package com.epam.task.textHandler.parser;

import com.epam.task.textHanling.entities.Component;
import com.epam.task.textHanling.entities.Composite;
import com.epam.task.textHanling.entities.Lexeme;
import com.epam.task.textHanling.parser.ParagraphParser;
import com.epam.task.textHanling.parser.Parser;
import com.epam.task.textHanling.parser.TextParser;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Arrays;

import static org.mockito.Mockito.when;

public class TextParserTest {

    private static final String TEXT = "First sentence. \nSecond. ";

    @Test
    public void parserShouldReturnComponentsParagraphs() {
        // given
        Lexeme lexemeFirst = Lexeme.word("First");
        Lexeme lexemeSecond = Lexeme.word("sentence");
        Lexeme lexemeThird = Lexeme.word("Second");
        Component sentenceFirst = new Composite(Arrays.asList(lexemeFirst, lexemeSecond));
        Component sentenceSecond = new Composite(Arrays.asList(lexemeThird));
        Component paragraphFirst = new Composite(Arrays.asList(sentenceFirst));
        Component paragraphSecond = new Composite(Arrays.asList(sentenceSecond));

        Parser paragraphParser = Mockito.mock(ParagraphParser.class);
        TextParser textParser = new TextParser(paragraphParser);

        String paragraphFirstAsText = "First sentence. ";
        when(paragraphParser.parse(paragraphFirstAsText)).thenReturn(paragraphFirst);

        String paragraphSecondAsText = "Second. ";
        when(paragraphParser.parse(paragraphSecondAsText)).thenReturn(paragraphSecond);

        Component expectedResult = new Composite(Arrays.asList(paragraphFirst, paragraphSecond));

        //when
        Component actualResult = textParser.parse(TEXT);

        //then
        Assert.assertEquals(expectedResult, actualResult);

    }

}
