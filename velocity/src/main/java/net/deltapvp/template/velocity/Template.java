/*
 * This file is part of Template, licensed under the MIT License.
 *
 * Copyright (c) 2023 powercas_gamer
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package net.deltapvp.template.velocity;

import com.google.inject.Inject;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent;
import com.velocitypowered.api.event.proxy.ProxyShutdownEvent;
import com.velocitypowered.api.plugin.Plugin;
import com.velocitypowered.api.plugin.annotation.DataDirectory;
import com.velocitypowered.api.proxy.ProxyServer;
import org.bstats.velocity.Metrics;
import org.slf4j.Logger;

import java.nio.file.Path;

@Plugin(
        id = "template",
        name = "@projectName@",
        version = "1.0.0",
        description = "@projectDescription@",
        authors = "@projectAuthor@"
)
public class Template {

    private static Template instance;
    private static final int PLUGIN_ID = 12345;

    private final ProxyServer server;
    private final Logger logger;
    private final Path dataDirectory;
    private final Metrics metrics;

    @Inject
    public Template(
            final ProxyServer server, final Logger logger, @DataDirectory final Path dataDirectory,
            final Metrics.Factory metricsFactory
    ) {
        instance = this;
        this.dataDirectory = dataDirectory;
        this.logger = logger;
        this.server = server;
        this.metrics = metricsFactory.make(this, PLUGIN_ID);
    }

    @Subscribe
    public void onProxyInitialization(final ProxyInitializeEvent event) {
    }

    @Subscribe
    public void onShutdown(final ProxyShutdownEvent event) {
        this.metrics.shutdown();
    }

    public static Template instance() {
        return instance;
    }

    public Path dataDirectory() {
        return this.dataDirectory;
    }

    public Logger logger() {
        return this.logger;
    }

    public ProxyServer server() {
        return this.server;
    }
}
