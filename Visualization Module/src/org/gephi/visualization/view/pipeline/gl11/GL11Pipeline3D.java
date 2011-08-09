/*
Copyright 2008-2011 Gephi
Authors : Antonio Patriarca <antoniopatriarca@gmail.com>
Website : http://www.gephi.org

This file is part of Gephi.

Gephi is free software: you can redistribute it and/or modify
it under the terms of the GNU Affero General Public License as
published by the Free Software Foundation, either version 3 of the
License, or (at your option) any later version.

Gephi is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU Affero General Public License for more details.

You should have received a copy of the GNU Affero General Public License
along with Gephi.  If not, see <http://www.gnu.org/licenses/>.
*/

package org.gephi.visualization.view.pipeline.gl11;

import javax.media.opengl.GL;
import org.gephi.visualization.data.FrameData;
import org.gephi.visualization.view.pipeline.Pipeline;

/**
 * 3D pipeline based on OpenGL 1.1
 *
 * @author Antonio Patriarca <antoniopatriarca@gmail.com>
 */
public class GL11Pipeline3D implements Pipeline {

    @Override
    public String name() {
        return "GL11Pipeline3D.";
    }

    @Override
    public String description() {
        return "3D pipeline based on OpenGL 1.1.";
    }

    @Override
    public String debugInfo() {
        return "";
    }

    final GL11NodePipeline3D nodePipeline;
    final GL11UIPipeline uiPipeline;

    public GL11Pipeline3D() {
        this.nodePipeline = new GL11NodePipeline3D();
        this.uiPipeline = new GL11UIPipeline();
    }

    @Override
    public boolean init(GL gl) {
        return this.nodePipeline.init(gl) && this.uiPipeline.init(gl);
    }

    @Override
    public void draw(GL gl, FrameData frameData) {
        this.nodePipeline.draw(gl, frameData);
        this.uiPipeline.draw(gl, frameData);
    }

    @Override
    public void dispose(GL gl) {
        this.nodePipeline.dispose(gl);
        this.uiPipeline.dispose(gl);
    }

    @Override
    public void reshape(GL gl, int x, int y, int w, int h) {
        int h2 = h == 0 ? 1 : h;

        gl.glViewport(0, 0, w, h2);
        
        this.nodePipeline.reshape(gl, x, y, w, h);
        this.uiPipeline.reshape(gl, x, y, w, h);
    }


}