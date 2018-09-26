package com.tao.tools;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 过滤富文本标签
 */
public class HtmlUtils {

    private static final String regEx_script = "<script[^>]*?>[\\s\\S]*?<\\/script>"; // 定义script的正则表达式
    private static final String regEx_style = "<style[^>]*?>[\\s\\S]*?<\\/style>"; // 定义style的正则表达式
    private static final String regEx_html = "<[^>]+>"; // 定义HTML标签的正则表达式
    private static final String regEx_space = "\\s*|\t|\r|\n";//定义空格回车换行符

    /**
     * @param htmlStr
     * @return
     * 删除Html标签
     */
    public static String delHTMLTag(String htmlStr) {
        Pattern p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);
        Matcher m_script = p_script.matcher(htmlStr);
        htmlStr = m_script.replaceAll(""); // 过滤script标签

        Pattern p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);
        Matcher m_style = p_style.matcher(htmlStr);
        htmlStr = m_style.replaceAll(""); // 过滤style标签

        Pattern p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
        Matcher m_html = p_html.matcher(htmlStr);
        htmlStr = m_html.replaceAll(""); // 过滤html标签

        Pattern p_space = Pattern.compile(regEx_space, Pattern.CASE_INSENSITIVE);
        Matcher m_space = p_space.matcher(htmlStr);
        htmlStr = m_space.replaceAll(""); // 过滤空格回车标签
        return htmlStr.trim(); // 返回文本字符串
    }

    public static String getTextFromHtml(String htmlStr){
        if(null==htmlStr){
            return "";
        }
        htmlStr = delHTMLTag(htmlStr);
        htmlStr = htmlStr.replaceAll("&nbsp;", "");
        return htmlStr;
    }

    public static void main(String[] args) {
        String str="<p>火币世界は最新の公告に基づき、8月18日の11:48:26、everipedia&nbsp;(iq)チームがユーザーや取引所に通知しないまま、スマート契約を更新した。新しいスマート契約では、加入者がiqを振替する時に振込み額の0.1%を手数料として払わなければならないし、その一部の手数料は直接everipediaのチームに入る。ユーザーの権益を守るために、火コイングローバル局は、iqの提札を緊急停止した。iqチームのこの行为は、ブロックチェーンに违反して透明な契约の精神を公表し、取引所のユーザーの権利に影响して、プロジェクト侧と协议して、火币グローバル局は8月31日18:00に、1时的にiqティアドルを回复することを决定して、30日、9月30日には、iqのホーキングを缔めることになっています。</p><p><br></p>";
        String htm=getTextFromHtml(str);
        System.out.println(htm);
    }
}
