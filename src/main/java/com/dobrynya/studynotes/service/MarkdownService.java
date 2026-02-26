package com.dobrynya.studynotes.service;

import com.dobrynya.studynotes.dto.HeadingInfo;
import org.commonmark.Extension;
import org.commonmark.ext.gfm.tables.TablesExtension;
import org.commonmark.node.AbstractVisitor;
import org.commonmark.node.Heading;
import org.commonmark.node.Node;
import org.commonmark.node.Text;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MarkdownService {
    private final Parser parser;
    private final HtmlRenderer renderer;

    public MarkdownService() {
        List<Extension> extensions = List.of(TablesExtension.create());
        this.parser = Parser.builder()
                .extensions(extensions)
                .build();
        this.renderer = HtmlRenderer.builder()
                .extensions(extensions)
                .build();
    }

    public String renderToHtml(String markdown) {
        if (markdown == null || markdown.isBlank()) {
            return "";
        }

        Node document = parser.parse(markdown);
        return renderer.render(document);
    }

    private static class HeadingVisitor extends AbstractVisitor {
        List<HeadingInfo> headings = new ArrayList<>();

        @Override
        public void visit(Heading heading) {
            if (heading.getLevel() >= 2 && heading.getLevel() <= 3) {
                String text = extractText(heading);
                headings.add(new HeadingInfo(heading.getLevel(), text));
            }
            visitChildren(heading);
        }

        private String extractText(Heading heading) {
            StringBuilder sb = new StringBuilder();
            heading.accept(new AbstractVisitor() {
                @Override
                public void visit(Text text) {
                    sb.append(text.getLiteral());
                }
            });
            return sb.toString().trim();
        }

        public List<HeadingInfo> getHeadings() {
            return headings;
        }
    }
}
