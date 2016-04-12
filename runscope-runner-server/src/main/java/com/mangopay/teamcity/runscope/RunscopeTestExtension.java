package com.mangopay.teamcity.runscope;

import jetbrains.buildServer.serverSide.STestRun;
import jetbrains.buildServer.web.openapi.PagePlaces;
import jetbrains.buildServer.web.openapi.PlaceId;
import jetbrains.buildServer.web.openapi.PluginDescriptor;
import jetbrains.buildServer.web.openapi.SimplePageExtension;
import org.jetbrains.annotations.NotNull;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RunscopeTestExtension extends SimplePageExtension {
    private final static String RUNSCOPE_TEST_LINK_ATTRIBUTE = "runscopeTestLink";

    private final static Pattern fullLogPattern;

    static{
        final String pattern = RunscopeConstants.LOG_SEE_FULL_LOG.replaceAll("%s", "(?<url>.*)");
        fullLogPattern = Pattern.compile(pattern);
    }
    public RunscopeTestExtension(@NotNull PagePlaces pagePlaces, @NotNull PluginDescriptor pluginDescriptor){
        super(pagePlaces, PlaceId.TEST_DETAILS_BLOCK, RunscopeConstants.PLUGIN_ID, pluginDescriptor.getPluginResourcesPath("runscopeTestDetail.jsp"));
        register();
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean isAvailable(@NotNull HttpServletRequest request) {
        final Object testRuns = request.getAttribute("testRuns");
        if(testRuns == null) return false;

        final List<? extends STestRun> runs = (List<? extends STestRun>)testRuns;
        if(runs != null && runs.size() < 1) return false;

        //finding test details
        for(int i = 0; i < runs.size(); i++) {
            final STestRun run = runs.get(i);
            final Matcher matcher = fullLogPattern.matcher(run.getFullText());
            if(!matcher.find()) continue;

            request.setAttribute(RUNSCOPE_TEST_LINK_ATTRIBUTE, matcher.group("url"));
            return true;
        }

        return false;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void fillModel(@NotNull Map<String, Object> model, @NotNull HttpServletRequest request) {
        final Object url = request.getAttribute(RUNSCOPE_TEST_LINK_ATTRIBUTE);
        model.put("url", url);
    }
}
