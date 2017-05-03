package manager.map;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.persistence.Query;

import javax.swing.ImageIcon;
import manager.window.MainManager;

import org.piccolo2d.*;
import org.piccolo2d.event.PBasicInputEventHandler;
import org.piccolo2d.event.PDragEventHandler;
import org.piccolo2d.event.PInputEvent;
import org.piccolo2d.event.PInputEventFilter;
import org.piccolo2d.nodes.PPath;
import org.piccolo2d.nodes.PText;

import manager.window.TransactionPopUp;
import org.piccolo2d.nodes.PImage;

public class GraphEditor extends PCanvas {

    PPath selected;
    Color selectColor = Color.GREEN;
    PLayer nodeLayer, edgeLayer, nameLayer, mapLayer;

    private boolean send;
    private TransactionPopUp sendTo;

    public GraphEditor(int width, int height) {
        setPreferredSize(new Dimension(width, height));
        send = false;

        // Initialize, and create a layer for the edges
        // (always underneath the nodes)
        nodeLayer = getLayer();
        edgeLayer = new PLayer();
        mapLayer = new PLayer();
        
        getRoot().addChild(mapLayer);
        mapLayer.setOffset(-500,-500);
        getCamera().addLayer(0, mapLayer);
        BufferedImage img = null;
        try {
            img = ImageIO.read(this.getClass().getResource("/map.png"));
        } catch (IOException ex) {
            Logger.getLogger(GraphEditor.class.getName()).log(Level.SEVERE, null, ex);
        }
        mapLayer.addChild(new PImage(img));
        
        getRoot().addChild(edgeLayer);
        getCamera().addLayer(1, edgeLayer);

        nameLayer = new PLayer();
        getRoot().addChild(nameLayer);
        getCamera().addLayer(2, nameLayer);

        //loadProvinces("province.txt");
        pullProvinces();
        selected = (PPath) nodeLayer.getChild(0);
        selected.setPaint(selectColor);

        // Create event handler to move nodes and update edges
        //nodeLayer.addInputEventListener(...);
        nodeLayer.addInputEventListener(new PBasicInputEventHandler() {
            {
                PInputEventFilter filter = new PInputEventFilter();
                filter.setOrMask(InputEvent.BUTTON1_MASK | InputEvent.BUTTON3_MASK);
                setEventFilter(filter);
            }

            public void mouseEntered(PInputEvent e) {
                super.mouseEntered(e);
                if (e.getButton() == MouseEvent.NOBUTTON) {
                    e.getPickedNode().setPaint(Color.YELLOW);
                }
            }

            public void mouseExited(PInputEvent e) {
                super.mouseExited(e);
                if (e.getButton() == MouseEvent.NOBUTTON) {
                    if (e.getPickedNode().equals(selected)) {
                        e.getPickedNode().setPaint(selectColor);
                    } else {
                        e.getPickedNode().setPaint(Color.WHITE);
                    }
                }
            }

            public void mousePressed(PInputEvent e) {
                super.mousePressed(e);
                selected.setPaint(Color.WHITE);
                selected = (PPath) e.getPickedNode();
                selected.setPaint(selectColor);
                if (send) {
                    sendTo.mapData(selected.getName());
                }
            }
        });
    }

    public void passTarget(TransactionPopUp target) {
        sendTo = target;
        send = true;
    }

    public void loadProvinces(String provinces) {
        File file = new File(provinces);
        if (file.exists()) {
            try {
                Scanner scan = new Scanner(file);
                String line = "";
                String[] split;
                String prov1, prov2;
                while (scan.hasNextLine()) {
                    line = scan.nextLine();
                    if (line.equals("#")) {
                        break;
                    }
                    int mx, my;
                    split = line.split(" ");
                    mx = Integer.parseInt(split[1].trim());
                    my = Integer.parseInt(split[2].trim());
                    prov1 = split[0].trim();
                    addProvince(mx, my, prov1);
                }
                while (scan.hasNextLine()) {
                    line = scan.nextLine();
                    split = line.split(" ");
                    prov1 = split[0].trim();
                    prov2 = split[1].trim();
                    connectProvinces(prov1, prov2);
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
    
    public void pullProvinces(){
        Query queryN = MainManager.getEM().createNativeQuery("SELECT province_name, x, y FROM province");
        List<Object[]> listN = queryN.getResultList();
        System.out.println("Pulling provinces:");
        for (Iterator<Object[]> itN = listN.iterator(); itN.hasNext();) {
            Object[] obj = itN.next();
            System.out.println(obj[0]+"["+obj[1]+","+obj[2]+"]");
            addProvince((float)((double)obj[1]),(float)((double)obj[2]),(String)obj[0]);
        }
        //their connections
        queryN = MainManager.getEM().createNativeQuery("SELECT p1.province_name, p2.province_name\n" +
"FROM province p1 join provinceneighbors pn\n" +
"ON p1.province_id = pn.prov1_province_id\n" +
"JOIN province p2 ON pn.prov2_province_id = p2.province_id");
        listN = queryN.getResultList();
        for (Iterator<Object[]> itN = listN.iterator(); itN.hasNext();) {
            Object[] obj = itN.next();
            System.out.println("["+obj[0]+" --- "+obj[1]+"]");
            connectProvinces((String)(obj[0]),(String)(obj[1]));
        }
    }

    public void addProvince(float x, float y, String name) {
        PPath node = PPath.createEllipse(x, y, 20, 20);
        node.addAttribute("edges", new ArrayList());
        node.setName(name);
        System.out.println(node.getName());
        nodeLayer.addChild(node);
        PText tag = new PText(name);
        tag.setBounds(x + 25, y + 4, 100, 100);
        nameLayer.addChild(tag);
    }

    public void connectProvinces(String a, String b) {
        PNode node1 = getNodeByName(a);
        PNode node2 = getNodeByName(b);
        if (node1 == null || node2 == null) {
            return;
        }
        PPath edge = PPath.createLine(node1.getX(), node1.getY(), node1.getX(), node1.getY());
        ((ArrayList) node1.getAttribute("edges")).add(edge);
        ((ArrayList) node2.getAttribute("edges")).add(edge);
        edge.addAttribute("nodes", new ArrayList());
        ((ArrayList) edge.getAttribute("nodes")).add(node1);
        ((ArrayList) edge.getAttribute("nodes")).add(node2);
        edgeLayer.addChild(edge);
        updateEdge(edge);
    }

    public PNode getNodeByName(String name) {
        Collection<PNode> nodes = nodeLayer.getAllNodes();
        for (PNode n : nodes) {
            String test = n.getName();
            if (test == null) {
                continue;
            }
            if (test.equals(name)) {
                return n;
            }
        }
        return null;
    }

    public void updateEdge(PPath edge) {
        // Note that the node's "FullBounds" must be used
        // (instead of just the "Bounds") because the nodes
        // have non-identity transforms which must be included
        // when determining their position.

        PNode node1 = (PNode) ((ArrayList) edge.getAttribute("nodes")).get(0);
        PNode node2 = (PNode) ((ArrayList) edge.getAttribute("nodes")).get(1);
        Point2D start = node1.getFullBoundsReference().getCenter2D();
        Point2D end = node2.getFullBoundsReference().getCenter2D();
        edge.reset();
        edge.moveTo((float) start.getX(), (float) start.getY());
        edge.lineTo((float) end.getX(), (float) end.getY());
    }
}
